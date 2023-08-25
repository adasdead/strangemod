package com.strangedevs.strangemod.item;

import com.strangedevs.strangemod.registry.ModGroups;
import com.strangedevs.strangemod.util.Tooltips;
import com.strangedevs.strangemod.util.Vectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AwakenedPickaxeItem extends PickaxeItem {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .durability(500)
            .tab(ModGroups.EXAMPLE_MOD);

    private static final Map<Block, Item> MELTING_RESULT = new HashMap<>();

    static {
        MELTING_RESULT.put(Blocks.IRON_ORE, Items.IRON_INGOT);
        MELTING_RESULT.put(Blocks.GOLD_ORE, Items.GOLD_INGOT);
    }

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
                            handleBlock(player, itemStack, pos.offset(0, i, j));
                            break;
                        case Y:
                            handleBlock(player, itemStack, pos.offset(i, 0, j));
                            break;
                        case Z:
                            handleBlock(player, itemStack, pos.offset(i, j, 0));
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

    private void handleBlock(@NotNull PlayerEntity player, @NotNull ItemStack stack, @NotNull BlockPos pos) {
        BlockState blockState = player.level.getBlockState(pos);

        if (player.isCreative()) {
            player.level.destroyBlock(pos, false);
            return;
        }

        if (!blockState.is(Blocks.AIR)) {
            Optional<Item> spawnItem = Optional
                    .ofNullable(MELTING_RESULT.get(blockState.getBlock()));

            if (getDestroySpeed(stack, blockState) != speed) {
                return;
            }

            if (spawnItem.isPresent()) {
                player.level.destroyBlock(pos, false);

                ItemEntity entity = new ItemEntity(EntityType.ITEM, player.level);
                Vector3d vPos = Vectors.toVec3(pos).add(Vectors.scalar(0.5));
                entity.setPos(vPos.x, vPos.y, vPos.z);
                entity.setItem(spawnItem.get().getDefaultInstance());
                player.level.addFreshEntity(entity);
            } else {
                player.level.destroyBlock(pos, true);
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable World world,
                                @NotNull List<ITextComponent> components,
                                @NotNull ITooltipFlag flag) {

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
