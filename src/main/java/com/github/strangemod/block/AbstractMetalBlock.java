package com.github.strangemod.block;

import com.github.strangemod.block.extra.RegisterBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public abstract class AbstractMetalBlock extends Block {
    private static final Block.Properties PROPERTIES = Block.Properties
            .of(Material.METAL, MaterialColor.DIAMOND)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL);

    public AbstractMetalBlock() {
        super(PROPERTIES);
    }

    @RegisterBlockItem(registryName = "awakened_iron_block")
    public static class AwakenedIronBlock extends AbstractMetalBlock {
    }

    @RegisterBlockItem(registryName = "steel_block")
    public static class SteelBlock extends AbstractMetalBlock {
    }
}
