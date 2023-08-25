package com.strangedevs.strangemod.block;

import com.strangedevs.strangemod.block.entity.ChipsStandBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.stream.Stream;

public class ChipsStandBlock extends DirectionalBlock {
    public ChipsStandBlock() {
        super(Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(1.5F, 6.0F)
                .noOcclusion());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return super.createTileEntity(state, world);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull BlockRenderType getRenderShape(@NotNull BlockState state) {
        return BlockRenderType.MODEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull ActionResultType use(@NotNull BlockState state,
                                          @NotNull World world,
                                          @NotNull BlockPos pos,
                                          @NotNull PlayerEntity player,
                                          @NotNull Hand hand,
                                          @NotNull BlockRayTraceResult result) {

        if (!world.isClientSide()) {
            getBlockEntity(world, pos).ifPresent(entity -> {
                NetworkHooks.openGui((ServerPlayerEntity) player, entity, entity.getBlockPos());
            });
        }

        return ActionResultType.sidedSuccess(world.isClientSide());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(@NotNull BlockState state,
                         @NotNull World world,
                         @NotNull BlockPos pos,
                         @NotNull BlockState newState,
                         boolean isMoving) {

        if (state.getBlock() != newState.getBlock()) {
            getBlockEntity(world, pos).ifPresent(ChipsStandBlockEntity::drop);
        }

        super.onRemove(state, world, pos, newState, isMoving);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state,
                                        @NotNull IBlockReader blockReader,
                                        @NotNull BlockPos pos,
                                        @NotNull ISelectionContext context) {
        return Stream.of(
                Block.box(5, 2, 5, 11, 9, 11),
                Block.box(3, 0, 3, 13, 2, 13),
                Block.box(3, 9, 3, 13, 10, 13),
                Block.box(3, 10, 12, 13, 11, 13),
                Block.box(3, 10, 3, 13, 11, 4),
                Block.box(3, 10, 4, 4, 11, 12),
                Block.box(12, 10, 4, 13, 11, 12)
        ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    }

    private Optional<ChipsStandBlockEntity> getBlockEntity(@NotNull World world, BlockPos pos) {
        TileEntity entity = world.getBlockEntity(pos);

        if (entity instanceof ChipsStandBlockEntity) {
            return Optional.of((ChipsStandBlockEntity) entity);
        }

        return Optional.empty();
    }
}
