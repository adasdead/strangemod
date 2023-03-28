package com.beetw.strangemod.block;

import com.beetw.strangemod.block.extra.RegisterBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
@RegisterBlockItem
public class MaxwellBlock extends Block {
    private static final Properties PROPERTIES = AbstractBlock.Properties
            .of(Material.WOOD)
            .lightLevel((state) -> 15);

    public MaxwellBlock() {
        super(PROPERTIES);

        RenderTypeLookup.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state,
                                        @NotNull IBlockReader blockReader,
                                        @NotNull BlockPos pos,
                                        @NotNull ISelectionContext context) {
        return Block.box(3, 2, 3, 14, 9, 13);
    }
}
