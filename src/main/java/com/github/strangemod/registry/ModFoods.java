package com.github.strangemod.registry;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CHIPS = new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.3f).build();

    public static final FoodProperties PILL = new FoodProperties.Builder()
            .nutrition(0).saturationMod(0.0f).build();
}
