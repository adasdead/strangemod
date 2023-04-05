package com.github.strangemod.registry;

import com.github.strangemod.StrangeMod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTINGS_TYPES =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, StrangeMod.MOD_ID);

    public static final RegistryObject<PaintingVariant> ADASDEAD =
            PAINTINGS_TYPES.register("adasdead", () -> new PaintingVariant(32, 32));

    public static final RegistryObject<PaintingVariant> SLIEKO =
            PAINTINGS_TYPES.register("slieko", () -> new PaintingVariant(32, 32));
}
