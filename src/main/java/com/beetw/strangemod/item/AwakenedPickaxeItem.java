package com.beetw.strangemod.item;

import com.beetw.strangemod.item.extra.ItemTooltipAppender;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class AwakenedPickaxeItem extends PickaxeItem {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1).durability(500);

    public AwakenedPickaxeItem() {
        super(Tiers.NETHERITE, 2, 3.0f, PROPERTIES);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, @NotNull Player player) {
        HitResult result = player.pick(25.0, 3.0f, true);

        if (getDestroySpeed(itemstack, player.level.getBlockState(pos)) != speed) {
            player.level.destroyBlock(pos, true);
        }

        if (result.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) result;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    switch (blockHitResult.getDirection().getAxis()) {
                        case X:
                            handleBlock(itemstack, player.level, pos.offset(0, i, j));
                            break;
                        case Y:
                            handleBlock(itemstack, player.level, pos.offset(i, 0, j));
                            break;
                        case Z:
                            handleBlock(itemstack, player.level, pos.offset(i, j, 0));
                            break;
                    }
                }
            }
        }

        itemstack.hurtAndBreak(5, player, livingEntity -> {
            livingEntity.broadcastBreakEvent(livingEntity.getUsedItemHand());
        });

        return true;
    }

    private void handleBlock(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);

        if (!blockState.is(Blocks.AIR)) {
            Optional<ItemStack> spawnItem = Optional.empty();

            if (getDestroySpeed(stack, blockState) != speed) {
                return;
            }

            if (blockState.is(Blocks.IRON_ORE)) {
                spawnItem = Optional.of(Items.IRON_INGOT.getDefaultInstance());
            }

            if (blockState.is(Blocks.SAND)) {
                spawnItem = Optional.of(Items.GLASS.getDefaultInstance());
            }

            if (blockState.is(Blocks.GOLD_ORE)) {
                spawnItem = Optional.of(Items.GOLD_INGOT.getDefaultInstance());
            }

            if (spawnItem.isPresent()) {
                ItemEntity entity = new ItemEntity(EntityType.ITEM, level);
                entity.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                entity.setItem(spawnItem.get());
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                level.addFreshEntity(entity);
            } else {
                level.destroyBlock(pos, true);
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level world,
                                @NotNull List<Component> components,
                                @NotNull TooltipFlag flag) {

        ItemTooltipAppender appender = new ItemTooltipAppender(components, true);
        appender.translate("awakened_pickaxe.0").translate("awakened_pickaxe.1");
        super.appendHoverText(stack, world, components, flag);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }
}
