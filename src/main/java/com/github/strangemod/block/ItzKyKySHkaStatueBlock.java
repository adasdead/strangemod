package com.github.strangemod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class ItzKyKySHkaStatueBlock extends HorizontalDirectionalBlock {
    private static final Block.Properties PROPERTIES = Block.Properties
            .of(Material.STONE)
            .lightLevel((state) -> 15)
            .strength(1.0f);

    public ItzKyKySHkaStatueBlock(){super(PROPERTIES);}


    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state,
                                        @NotNull BlockGetter getter,
                                        @NotNull BlockPos pos,
                                        @NotNull CollisionContext context) {
        return Stream.of(

                Block.box(2, 0, 2, 14, 2, 14),
                Block.box(4.1, 0, 6, 8.1, 12, 10),
                Block.box(7.9, 0, 6, 11.9, 12, 10),
                Block.box(12, 12, 6, 16, 24, 10),
                Block.box(0, 12, 6, 4, 24, 10),
                Block.box(4, 12, 6, 12, 24, 10),
                Block.box(4, 23.5, 4, 12, 31.5, 12)

        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    }
}

