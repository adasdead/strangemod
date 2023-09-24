package com.github.strangemod.registry;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.github.strangemod.StrangeMod;
import org.jetbrains.annotations.NotNull;

public final class ModCrops {
    public static final Crop awakened_iron = new Crop(
            StrangeMod.location("awakened_iron"),
            CropTier.FIVE, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 25512724,
            LazyIngredient.item("strange_mod:awakened_iron_ingot"));

    public static void onRegisterCrops(@NotNull ICropRegistry registry) {
        registry.register(awakened_iron);
    }

    public static void onPostRegisterCrops(ICropRegistry registry) {
        awakened_iron.setCruxBlock(ModBlocks.AWAKENED_IRON_BLOCK);
    }
}