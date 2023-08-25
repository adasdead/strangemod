package com.strangedevs.strangemod.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

public class ItzKyKySHkaStatueBlock extends HorizontalBlock {
    private static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    private static final VoxelShape SHAPE = Stream.of(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(4.1, 0, 6, 8.1, 12, 10),
            Block.box(7.9, 0, 6, 11.9, 12, 10),
            Block.box(12, 12, 6, 16, 24, 10),
            Block.box(0, 12, 6, 4, 24, 10),
            Block.box(4, 12, 6, 12, 24, 10),
            Block.box(4, 23.5, 4, 12, 31.5, 12)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    public ItzKyKySHkaStatueBlock() {
        super(AbstractBlock.Properties
                .of(Material.METAL)
                .lightLevel((state) -> 15)
                .strength(1.0f)
                .noOcclusion());

        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));

        for (Direction direction : Direction.values()) {
            SHAPES.put(direction, rotateShape(direction));
        }
    }

    // https://www.youtube.com/watch?v=ngSgStS-gm4
    private VoxelShape rotateShape(@NotNull Direction to) {
        final VoxelShape[] buffer = {ItzKyKySHkaStatueBlock.SHAPE, VoxelShapes.empty()};

        final int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.or(buffer[1],
                    VoxelShapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        return buffer[0];
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state,
                                        @NotNull IBlockReader reader,
                                        @NotNull BlockPos pos,
                                        @NotNull ISelectionContext context) {

        return SHAPES.get(state.getValue(FACING));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockItemUseContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}

