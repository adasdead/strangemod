package com.strangedevs.strangemod.item;

import com.strangedevs.strangemod.registry.ModFoods;
import com.strangedevs.strangemod.registry.ModGroups;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ChipsItem extends Item {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .food(ModFoods.CHIPS)
            .durability(200)
            .tab(ModGroups.EXAMPLE_MOD);

    public ChipsItem() {
        super(PROPERTIES);
    }

    public static float itemProperties(@NotNull ItemStack itemStack,
                                       @Nullable ClientWorld world,
                                       @Nullable LivingEntity entity) {


        return entity != null && entity.isUsingItem() &&
                entity.getUseItem() == itemStack ? 1.0f : 0.0f;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack,
                                              @NotNull World world,
                                              @NotNull LivingEntity entity) {

        ItemStack copyStack = itemStack.copy();

        super.finishUsingItem(itemStack, world, entity);

        if (entity instanceof PlayerEntity) {
            if (!((PlayerEntity) entity).isCreative()) {
                copyStack.hurt(10, new Random(), null);

                if (copyStack.getDamageValue() >= copyStack.getMaxDamage()) {
                    copyStack = ItemStack.EMPTY;
                }
            }
        }

        return copyStack;
    }
}