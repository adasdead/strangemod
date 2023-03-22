package com.beetw.examplemod.item;

import com.beetw.examplemod.init.ModFoods;
import com.beetw.examplemod.init.ModGroups;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
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
            .durability(100)
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
        copyStack.hurt(10, new Random(), null);

        if (copyStack.getDamageValue() >= copyStack.getMaxDamage()) {
            copyStack = ItemStack.EMPTY;
        }

        super.finishUsingItem(itemStack, world, entity);

        return copyStack;
    }
}
