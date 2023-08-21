package com.strangedevs.strangemod.registry;

import net.minecraft.item.Food;

public class ModFoods {
    public static final Food CHIPS = new Food.Builder().nutrition(2)
            .saturationMod(0.3f).build();
}
