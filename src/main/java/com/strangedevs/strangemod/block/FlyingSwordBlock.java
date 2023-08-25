package com.strangedevs.strangemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class FlyingSwordBlock extends DirectionalBlock {
    public FlyingSwordBlock() {
        super(Properties.of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
                .noOcclusion());
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state,
                                        @NotNull IBlockReader blockReader,
                                        @NotNull BlockPos pos,
                                        @NotNull ISelectionContext context) {
        return Stream.of(
                Block.box(1, 0, 1, 15, 1, 15),
                Block.box(1, 8, 1, 15, 9, 15),
                Block.box(2, 1, 2, 14, 8, 14)
        ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    }
}
