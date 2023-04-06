package com.github.strangemod.item;

import net.minecraft.world.item.Item;

public class AwakingStaffItem extends Item {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1).durability(1000);

    public AwakingStaffItem() {
        super(PROPERTIES);
    }
}