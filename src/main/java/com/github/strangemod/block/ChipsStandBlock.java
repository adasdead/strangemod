package com.github.strangemod.block;

import com.github.strangemod.block.entity.ChipsStandBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.stream.Stream;

public class ChipsStandBlock extends BaseEntityBlock {
    public ChipsStandBlock() {
        super(BlockBehaviour.Properties.of()
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops()
                .strength(1.5F, 6.0F)
                .noOcclusion());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState state) {
        return new ChipsStandBlockEntity(blockPos, state);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state,
                                          @NotNull Level level,
                                          @NotNull BlockPos pos,
                                          @NotNull Player player,
                                          @NotNull InteractionHand hand,
                                          @NotNull BlockHitResult result) {

        if (!level.isClientSide()) {
            getBlockEntity(level, pos).ifPresent(entity -> {
                NetworkHooks.openScreen((ServerPlayer) player, entity, pos);
            });
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(@NotNull BlockState state,
                         @NotNull Level level,
                         @NotNull BlockPos pos,
                         @NotNull BlockState newState,
                         boolean isMoving) {

        if (state.getBlock() != newState.getBlock()) {
            getBlockEntity(level, pos).ifPresent(ChipsStandBlockEntity::drop);
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state,
                                        @NotNull BlockGetter getter,
                                        @NotNull BlockPos pos,
                                        @NotNull CollisionContext context) {
        return Stream.of(
                Block.box(5, 2, 5, 11, 9, 11),
                Block.box(3, 0, 3, 13, 2, 13),
                Block.box(3, 9, 3, 13, 10, 13),
                Block.box(3, 10, 12, 13, 11, 13),
                Block.box(3, 10, 3, 13, 11, 4),
                Block.box(3, 10, 4, 4, 11, 12),
                Block.box(12, 10, 4, 13, 11, 12)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    }

    private Optional<ChipsStandBlockEntity> getBlockEntity(@NotNull Level level, BlockPos pos) {
        BlockEntity entity = level.getBlockEntity(pos);

        if (entity instanceof ChipsStandBlockEntity) {
            return Optional.of((ChipsStandBlockEntity) entity);
        }

        return Optional.empty();
    }
}
