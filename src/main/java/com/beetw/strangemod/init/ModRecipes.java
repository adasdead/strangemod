package com.beetw.strangemod.init;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.item.crafting.AwakingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, StrangeMod.MOD_ID);

    public static RegistryObject<SpecialRecipeSerializer<?>> AWAKING_STAFF_RECIPE = RECIPES
            .register("crafting_special_awaking",
                    () -> new SpecialRecipeSerializer<>(AwakingRecipe::new));
}
