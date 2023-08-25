package com.strangedevs.strangemod.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public abstract class AbstractMetalBlock extends Block {
    public AbstractMetalBlock() {
        super(AbstractBlock.Properties
                .of(Material.METAL)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE)
                .strength(8.0f));
    }

    public static class Impl extends AbstractMetalBlock {
    }
}
