package com.github.strangemod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class CoalCoke extends Item {


    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(16);

    public CoalCoke(Properties p_41383_) {
        super(p_41383_);

    }

    public CoalCoke() {
        super(new Properties());

    }


    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 3200;
    }
}