package com.github.strangemod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

public class ItzKyKySHkaStatueBlock extends HorizontalDirectionalBlock {
    private static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    private static final VoxelShape SHAPE = Stream.of(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(4.1, 0, 6, 8.1, 12, 10),
            Block.box(7.9, 0, 6, 11.9, 12, 10),
            Block.box(12, 12, 6, 16, 24, 10),
            Block.box(0, 12, 6, 4, 24, 10),
            Block.box(4, 12, 6, 12, 24, 10),
            Block.box(4, 23.5, 4, 12, 31.5, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ItzKyKySHkaStatueBlock() {
        super(Block.Properties
                .of()
                .lightLevel((state) -> 15)
                .strength(1.0f));

        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));

        for (Direction direction : Direction.values()) {
            SHAPES.put(direction, rotateShape(direction));
        }
    }

    // https://www.youtube.com/watch?v=ngSgStS-gm4
    private VoxelShape rotateShape(@NotNull Direction to) {
        final VoxelShape[] buffer = {ItzKyKySHkaStatueBlock.SHAPE, Shapes.empty()};

        final int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1],
                    Shapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }

        return buffer[0];
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state,
                                        @NotNull BlockGetter level,
                                        @NotNull BlockPos pos,
                                        @NotNull CollisionContext context) {

        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

}

