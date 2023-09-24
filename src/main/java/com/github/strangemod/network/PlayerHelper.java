package com.github.strangemod.network;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;


public final class PlayerHelper {



    public static boolean checkedReplaceBlock(ServerPlayer player, BlockPos pos, BlockState state) {
        if (!hasEditPermission(player, pos)) {
            return false;
        }
        Level level = player.getCommandSenderWorld();
        BlockSnapshot before = BlockSnapshot.create(level.dimension(), level, pos);
        level.setBlockAndUpdate(pos, state);
        BlockEvent.EntityPlaceEvent evt = new BlockEvent.EntityPlaceEvent(before, Blocks.AIR.defaultBlockState(), player);
        MinecraftForge.EVENT_BUS.post(evt);
        if (evt.isCanceled()) {
            level.restoringBlockSnapshots = true;
            before.restore(true, false);
            level.restoringBlockSnapshots = false;

            return false;
        }
   return true;
    }
    public static ItemStack findFirstItem(Player player, Item consumeFrom) {
        return player.getInventory().items.stream().filter(s -> !s.isEmpty() && s.getItem() == consumeFrom).findFirst().orElse(ItemStack.EMPTY);
    }


    public static BlockHitResult getBlockLookingAt(Player player, double maxDistance) {
        Pair<Vec3, Vec3> vecs = getLookVec(player, maxDistance);
        ClipContext ctx = new ClipContext(vecs.getLeft(), vecs.getRight(), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player);
        return player.getCommandSenderWorld().clip(ctx);
    }

    /**
     * Returns a vec representing where the player is looking, capped at maxDistance away.
     */
    public static Pair<Vec3, Vec3> getLookVec(Player player, double maxDistance) {
        // Thank you ForgeEssentials
        Vec3 look = player.getViewVector(1.0F);
        Vec3 playerPos = new Vec3(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());
        Vec3 src = playerPos.add(0, player.getEyeHeight(), 0);
        Vec3 dest = src.add(look.x * maxDistance, look.y * maxDistance, look.z * maxDistance);
        return ImmutablePair.of(src, dest);
    }

    public static boolean hasBreakPermission(ServerPlayer player, BlockPos pos) {
        return hasEditPermission(player, pos) && ForgeHooks.onBlockBreakEvent(player.getCommandSenderWorld(), player.gameMode.getGameModeForPlayer(), player, pos) != -1;
    }

    public static boolean hasEditPermission(ServerPlayer player, BlockPos pos) {
        if (ServerLifecycleHooks.getCurrentServer().isUnderSpawnProtection((ServerLevel) player.getCommandSenderWorld(), pos, player)) {
            return false;
        }
        return Arrays.stream(Direction.values()).allMatch(e -> player.mayUseItemAt(pos, e, ItemStack.EMPTY));
    }


    public static void swingItem(Player player, InteractionHand hand) {
        if (player.getCommandSenderWorld() instanceof ServerLevel level) {
            level.getChunkSource().broadcastAndSend(player, new ClientboundAnimatePacket(player, hand == InteractionHand.MAIN_HAND ? 0 : 3));
        }
    }


}
