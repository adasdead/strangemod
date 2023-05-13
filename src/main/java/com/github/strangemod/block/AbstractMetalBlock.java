package com.github.strangemod.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public abstract class AbstractMetalBlock extends Block {
    public AbstractMetalBlock() {
        super(Block.Properties
                .of(Material.METAL, MaterialColor.DIAMOND)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL));
    }

    public static class Impl extends AbstractMetalBlock {
    }
}
