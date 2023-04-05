package com.github.strangemod.registry;


import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.github.strangemod.registry.ModBlocks.STEEL_BLOCK;

// TODO: gotta fix this shit
public final class ModCrops {
    public static final Crop AWAKING_IRON_INGOT = new Crop(
            new ResourceLocation("mysticalagriculture", "awaking_iron_ingot"),
            CropTier.THREE, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 25512724,
            LazyIngredient.item("strange_mod:awaking_iron_ingot"));

    public ModCrops() {
    }

    public static void onRegisterCrops(@NotNull ICropRegistry registry) {
        registry.register(withRequiredMods("strange_mod"));
    }

    public static void onPostRegisterCrops(ICropRegistry registry) {
        AWAKING_IRON_INGOT.setCruxBlock(STEEL_BLOCK);
    }

    private static Crop withRequiredMods(String... mods) {
        if (!FMLEnvironment.production) return ModCrops.AWAKING_IRON_INGOT;

        var enabled = Arrays.stream(mods).anyMatch(ModList.get()::isLoaded);
        return ModCrops.AWAKING_IRON_INGOT.setEnabled(enabled);
    }
}