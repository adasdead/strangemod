package com.beetw.examplemod.item.custom;


import net.minecraft.item.*;

public class awaking_staff extends Item {
    public awaking_staff(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if(container.attemptDamageItem(1, random, null)) {
            return ItemStack.EMPTY;
        }
        else {
            return container;
        }
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}