package com.github.strangemod.item;

import com.github.strangemod.util.Tooltips;
import com.github.strangemod.util.Vectors;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AwakenedPickaxeItem extends PickaxeItem {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1).durability(500);

    private static final Map<Block, Item> MELTING_RESULT = new HashMap<>();

    static {
        MELTING_RESULT.put(Blocks.IRON_ORE, Items.IRON_INGOT);
        MELTING_RESULT.put(Blocks.GOLD_ORE, Items.GOLD_INGOT);
    }

    public AwakenedPickaxeItem() {
        super(Tiers.NETHERITE, 2, 3.0f, PROPERTIES);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, @NotNull Player player) {
        HitResult hitResult = player.pick(25.0, 3.0f, true);

        if (getDestroySpeed(stack, player.level().getBlockState(pos)) != speed) {
            player.level().destroyBlock(pos, true);
        }

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    switch (blockHitResult.getDirection().getAxis()) {
                        case X -> handleBlock(player, stack, pos.offset(0, i, j));
                        case Y -> handleBlock(player, stack, pos.offset(i, 0, j));
                        case Z -> handleBlock(player, stack, pos.offset(i, j, 0));
                    }
                }
            }
        }

        stack.hurtAndBreak(5, player, livingEntity -> {
            livingEntity.broadcastBreakEvent(livingEntity.getUsedItemHand());
        });

        return true;
    }

    private void handleBlock(@NotNull Player player, @NotNull ItemStack stack,
                             @NotNull BlockPos pos) {

        Level playerLevel = player.level();
        BlockState blockState = playerLevel.getBlockState(pos);

        if (player.isCreative()) {
            playerLevel.destroyBlock(pos, false);
            return;
        }

        if (!blockState.is(Blocks.AIR)) {
            Optional<Item> spawnItem = Optional
                    .ofNullable(MELTING_RESULT.get(blockState.getBlock()));

            if (getDestroySpeed(stack, blockState) != speed) {
                return;
            }

            if (spawnItem.isPresent()) {
                playerLevel.destroyBlock(pos, false);

                ItemEntity entity = new ItemEntity(EntityType.ITEM, playerLevel);
                entity.setPos(Vectors.toVec3(pos).add(Vectors.scalar(0.5)));
                entity.setItem(spawnItem.get().getDefaultInstance());
                playerLevel.addFreshEntity(entity);
            } else {
                playerLevel.destroyBlock(pos, true);
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level world,
                                @NotNull List<Component> components,
                                @NotNull TooltipFlag flag) {

        Tooltips.Appender appender = Tooltips.appender(components);
        appender.translate("tooltip.strange_mod.awakened_pickaxe.0");
        appender.translate("tooltip.strange_mod.awakened_pickaxe.1");

        MELTING_RESULT.keySet().forEach(block -> {
            appender.append(" - " + block.getName().getString());
        });

        super.appendHoverText(stack, world, components, flag);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }
}
