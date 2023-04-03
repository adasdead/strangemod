package com.beetw.strangemod.registry;

import com.beetw.strangemod.StrangeMod;
import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ModPaintings {
    public static final DeferredRegister<PaintingType> PAINTINGS_TYPES =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, StrangeMod.MOD_ID);

    public static final RegistryObject<PaintingType> ADASDEAD =
            PAINTINGS_TYPES.register("adasdead", () -> new PaintingType(32, 32));

    public static final RegistryObject<PaintingType> SLIEKO =
            PAINTINGS_TYPES.register("slieko", () -> new PaintingType(32, 32));
}
