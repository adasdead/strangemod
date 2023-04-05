package com.github.strangemod.registry;


import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.github.strangemod.block.AbstractMetalBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;


import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static com.github.strangemod.registry.ModBlocks.STEEL_BLOCK;


public final class ModCrops {

    private static final boolean DEBUG = !FMLEnvironment.production;

    public static final Crop AWAKING_IRON_INGOT = new Crop(new ResourceLocation("mysticalagriculture", "awaking_iron_ingot"), CropTier.THREE, CropType.RESOURCE, CropTextures.INGOT_CROP_TEXTURES, 25512724, LazyIngredient.item("strangmod:awaking_iron_ingot"));


    public ModCrops() {
    }

    public static void onRegisterCrops(ICropRegistry registry) {
        registry.register(withRequiredMods(AWAKING_IRON_INGOT, "strangemod"));
    }
    public static void onPostRegisterCrops(ICropRegistry registry) {
    AWAKING_IRON_INGOT.setCruxBlock(STEEL_BLOCK);
    }

    private static Crop withRequiredMods(Crop crop, String... mods) {
        if (DEBUG) return crop;

        var enabled = Arrays.stream(mods).anyMatch(ModList.get()::isLoaded);
        return crop.setEnabled(enabled);



}
}