package com.strangedevs.strangemod.block.extra;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

@RegisterBlockItem
public class MetalBlock extends Block {
    private static final Properties PROPERTIES = AbstractBlock.Properties
            .of(Material.METAL)
            .harvestLevel(1)
            .harvestTool(ToolType.PICKAXE)
            .strength(8.0f);

    public MetalBlock() {
        super(PROPERTIES);
    }
}
