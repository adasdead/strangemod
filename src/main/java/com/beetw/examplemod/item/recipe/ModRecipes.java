package com.beetw.examplemod.item.recipe;

import com.beetw.examplemod.ExampleMod;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRecipes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ExampleMod.MOD_ID);

    public static RegistryObject<SpecialRecipeSerializer<AwakingIronIngotRecipe>> AWAKING_STAFF_RECIPE;

    static {
        AWAKING_STAFF_RECIPE = RECIPES.register("awaking_iron_ingot_recipe",
                () -> new SpecialRecipeSerializer<>(AwakingIronIngotRecipe::new));
    }
}
