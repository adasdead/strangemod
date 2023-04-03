package com.beetw.strangemod.item;

import com.beetw.strangemod.item.extra.ItemTooltipAppender;
import com.beetw.strangemod.registry.ModGroups;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class AwakenedPickaxeItem extends PickaxeItem {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .durability(500)
            .tab(ModGroups.EXAMPLE_MOD);

    public AwakenedPickaxeItem() {
        super(ItemTier.NETHERITE, 2, 3.0f, PROPERTIES);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemStack, BlockPos pos, @NotNull PlayerEntity player) {
        RayTraceResult result = player.pick(25.0, 3.0f, true);

        if (getDestroySpeed(itemStack, player.level.getBlockState(pos)) != speed) {
            player.level.destroyBlock(pos, true);
        }

        if (result.getType() == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockResult = (BlockRayTraceResult) result;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    switch (blockResult.getDirection().getAxis()) {
                        case X:
                            handleBlock(itemStack, player.level, pos.offset(0, i, j));
                            break;
                        case Y:
                            handleBlock(itemStack, player.level, pos.offset(i, 0, j));
                            break;
                        case Z:
                            handleBlock(itemStack, player.level, pos.offset(i, j, 0));
                            break;
                    }
                }
            }
        }

        itemStack.hurtAndBreak(5, player, livingEntity -> {
            livingEntity.broadcastBreakEvent(livingEntity.getUsedItemHand());
        });

        return true;
    }

    private void handleBlock(@NotNull ItemStack stack, @NotNull World world, @NotNull BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();

        if (!block.is(Blocks.AIR)) {
            Optional<ItemStack> spawnItem = Optional.empty();

            if (getDestroySpeed(stack, block.defaultBlockState()) != speed) {
                return;
            }

            if (block.is(Blocks.IRON_ORE)) {
                spawnItem = Optional.of(Items.IRON_INGOT.getDefaultInstance());
            }

            if (block.is(Blocks.SAND)) {
                spawnItem = Optional.of(Items.GLASS.getDefaultInstance());
            }

            if (block.is(Blocks.GOLD_ORE)) {
                spawnItem = Optional.of(Items.GOLD_INGOT.getDefaultInstance());
            }

            if (spawnItem.isPresent()) {
                ItemEntity entity = new ItemEntity(EntityType.ITEM, world);
                entity.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                entity.setItem(spawnItem.get());
                world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                world.addFreshEntity(entity);
            } else {
                world.destroyBlock(pos, true);
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable World world,
                                @NotNull List<ITextComponent> components,
                                @NotNull ITooltipFlag flag) {

        ItemTooltipAppender appender = new ItemTooltipAppender(components, true);
        appender.translate("awakened_pickaxe.0").translate("awakened_pickaxe.1");
        super.appendHoverText(stack, world, components, flag);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }
}
