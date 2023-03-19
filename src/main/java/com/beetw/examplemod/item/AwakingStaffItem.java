package com.beetw.examplemod.item;

import com.beetw.examplemod.init.ModGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraftforge.common.ForgeHooks;

public class AwakingStaffItem extends SwordItem {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .durability(100)
            .tab(ModGroups.EXAMPLE_MOD);

    public AwakingStaffItem() {
        super(ItemTier.IRON, 2, 3.0f, PROPERTIES);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return ForgeHooks.getContainerItem(itemStack);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}