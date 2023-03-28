package com.beetw.strangemod.block;

import com.beetw.strangemod.block.extra.RegisterBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

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
        return Stream.of(
                Block.box(4.5, 4.5, 4.5, 11.5, 11.5, 11.5),
                Block.box(12, 4, 0, 16, 12, 4),
                Block.box(0, 4, 12, 4, 12, 16),
                Block.box(12, 4, 12, 16, 12, 16),
                Block.box(0, 4, 0, 4, 12, 4),
                Block.box(0, 12, 0, 16, 16, 16),
                Block.box(0, 0, 0, 16, 4, 16)
        ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    }
}
