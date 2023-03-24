package com.beetw.strangemod.init;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.block.GunpowderBarrelBlock;
import com.beetw.strangemod.block.WoodCasingBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, StrangeMod.MOD_ID);

    public static final RegistryObject<Block> GUNPOWDER_BARREL = BLOCKS.register(
            "gunpowder_barrel", GunpowderBarrelBlock::new);

    public static final RegistryObject<Block> WOOD_CASING = BLOCKS.register(
            "wood_casing", WoodCasingBlock::new);
}