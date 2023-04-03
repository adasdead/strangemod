package com.beetw.strangemod.registry;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.block.GunpowderBarrelBlock;
import com.beetw.strangemod.block.WoodCasingBlock;
import com.beetw.strangemod.block.extra.MetalBlock;
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

    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register(
            "steel_block", MetalBlock::new);

    public static final RegistryObject<Block> AWAKENED_IRON_BLOCK = BLOCKS.register(
            "awakened_iron_block", MetalBlock::new);

    public static final RegistryObject<Block> MAXWELL = BLOCKS.register(
            "maxwell_block", WoodCasingBlock::new);
}
