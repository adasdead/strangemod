package com.github.strangemod.item;

import com.github.strangemod.network.PlayerHelper;
import com.github.strangemod.registry.ModFoods;
import com.github.strangemod.registry.ModTransmutations;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class PotatoKnishes extends Item {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1).rarity(Rarity.EPIC);
    // .food(ModFoods.POTATO_KNISH);

    public PotatoKnishes() {
        super(PROPERTIES);
    }

    public static Map<BlockPos, BlockState> getChanges(Level level, BlockPos pos, Player player, Direction sideHit, int mode, int charge) {
        BlockState targeted = level.getBlockState(pos);
        boolean isSneaking = player.isSecondaryUseActive();
        BlockState result = ModTransmutations.getModTransmutation(targeted, isSneaking);
        if (result == null) {
            //Targeted block has no transmutations, no positions
            return Collections.emptyMap();
        }
        Stream<BlockPos> stream = null;
        switch (mode) {
            // Cube
            case 0 -> stream = BlockPos.betweenClosedStream(pos.offset(-charge, -charge, -charge), pos.offset(charge, charge, charge));
            case 1 -> {// Panel
                if (sideHit == Direction.UP || sideHit == Direction.DOWN) {
                    stream = BlockPos.betweenClosedStream(pos.offset(-charge, 0, -charge), pos.offset(charge, 0, charge));
                } else if (sideHit == Direction.EAST || sideHit == Direction.WEST) {
                    stream = BlockPos.betweenClosedStream(pos.offset(0, -charge, -charge), pos.offset(0, charge, charge));
                } else if (sideHit == Direction.SOUTH || sideHit == Direction.NORTH) {
                    stream = BlockPos.betweenClosedStream(pos.offset(-charge, -charge, 0), pos.offset(charge, charge, 0));
                }
            }
            case 2 -> {// Line
                Direction playerFacing = player.getDirection();
                if (playerFacing.getAxis() == Direction.Axis.Z) {
                    stream = BlockPos.betweenClosedStream(pos.offset(0, 0, -charge), pos.offset(0, 0, charge));
                } else if (playerFacing.getAxis() == Direction.Axis.X) {
                    stream = BlockPos.betweenClosedStream(pos.offset(-charge, 0, 0), pos.offset(charge, 0, 0));
                }
            }
        }
        if (stream == null) {
            return Collections.emptyMap();
        }
        Map<BlockState, BlockState> conversions = new Object2ObjectArrayMap<>();
        conversions.put(targeted, result);
        Map<BlockPos, BlockState> changes = new HashMap<>();
        Block targetBlock = targeted.getBlock();
        stream.forEach(currentPos -> {
            BlockState state = level.getBlockState(currentPos);
            if (state.is(targetBlock)) {
                BlockState actualResult;
                if (conversions.containsKey(state)) {
                    actualResult = conversions.get(state);
                } else {
                    conversions.put(state, actualResult = ModTransmutations.getModTransmutation(state, isSneaking));
                }
                //We allow for null keys to avoid having to look it up again from the world transmutations
                // which may be slightly slower, but we only add it as a position to change if we have a result
                if (actualResult != null) {
                    changes.put(currentPos.immutable(), actualResult);
                }
            }
        });
        return changes;}
    public BlockHitResult getHitBlock(Player player) {
        return getPlayerPOVHitResult(player.getCommandSenderWorld(), player, player.isSecondaryUseActive() ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
    }
    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Player player = ctx.getPlayer();
        if (player == null) {
            return InteractionResult.FAIL;
        }
        BlockPos pos = ctx.getClickedPos();
        Direction sideHit = ctx.getClickedFace();
        Level level = ctx.getLevel();
        ItemStack stack = ctx.getItemInHand();

        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        BlockHitResult rtr = getHitBlock(player);
        if (rtr.getType() == HitResult.Type.BLOCK && !rtr.getBlockPos().equals(pos)) {
            pos = rtr.getBlockPos();
            sideHit = rtr.getDirection();
        }
        Map<BlockPos, BlockState> toChange = getChanges(level, pos, player, getMode(stack), getCharge(stack));
        if (!toChange.isEmpty()) {
            for (Map.Entry<BlockPos, BlockState> entry : toChange.entrySet()) {
                BlockPos currentPos = entry.getKey();
               PlayerHelper.checkedReplaceBlock((ServerPlayer) player, currentPos, entry.getValue());
                if (level.random.nextInt(8) == 0) {
                    ((ServerLevel) level).sendParticles(ParticleTypes.LARGE_SMOKE, currentPos.getX(), currentPos.getY() + 1, currentPos.getZ(), 2, 0, 0, 0, 0);
                }
            }

        }
        return InteractionResult.SUCCESS;
    }

    private Map<BlockPos, BlockState> getChanges(Level level, BlockPos pos, Player player, Object mode, Object charge) {
        return null;
    }

    private Object getCharge(ItemStack stack) {
        return null;
    }

    private Object getMode(ItemStack stack) {
        return null;
    }

}


