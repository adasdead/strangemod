package com.beetw.examplemod.init;

import com.beetw.examplemod.ExampleMod;
import com.beetw.examplemod.item.crafting.AwakingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ExampleMod.MOD_ID);

    public static RegistryObject<SpecialRecipeSerializer<?>> AWAKING_STAFF_RECIPE = RECIPES
            .register("crafting_special_awaking",
                    () -> new SpecialRecipeSerializer<>(AwakingRecipe::new));
}
