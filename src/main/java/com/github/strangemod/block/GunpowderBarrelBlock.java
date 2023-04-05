package com.github.strangemod.block;

import com.github.strangemod.block.extra.RegisterBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

@SuppressWarnings("deprecation")
@RegisterBlockItem(registryName = "gunpowder_barrel")
public class GunpowderBarrelBlock extends FallingBlock {
    private static final Block.Properties PROPERTIES = Block.Properties
            .of(Material.WOOD)
            .strength(1.0f);

    private static final float EXPLODE_RADIUS = 5.0f;

    public GunpowderBarrelBlock() {
        super(PROPERTIES);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState,
                                          @NotNull Level level,
                                          @NotNull BlockPos pos,
                                          @NotNull Player player,
                                          @NotNull InteractionHand hand,
                                          @NotNull BlockHitResult hitResult) {

        ItemStack itemInHand = player.getItemInHand(hand);
        Item item = itemInHand.getItem();

        if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) {
            return super.use(blockState, level, pos, player, hand, hitResult);
        } else {
            this.onCaughtFire(blockState, level, pos, hitResult.getDirection(), player);

            if (!player.isCreative()) {
                if (item == Items.FLINT_AND_STEEL) {
                    itemInHand.hurtAndBreak(1, player,
                            entity -> entity.broadcastBreakEvent(hand));
                } else {
                    itemInHand.shrink(1);
                }
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public void onPlace(@NotNull BlockState state,
                        @NotNull Level level,
                        @NotNull BlockPos pos,
                        @NotNull BlockState state2,
                        boolean magic) {

        if (level.hasNeighborSignal(pos)) {
            this.explode(level, pos, null);
        } else {
            super.onPlace(state, level, pos, state2, magic);
        }
    }

    @Override
    public void onLand(@NotNull Level world,
                       @NotNull BlockPos pos,
                       @NotNull BlockState state,
                       @NotNull BlockState state2,
                       @NotNull FallingBlockEntity entity) {

        this.explode(world, pos, null);
    }

    @Override
    public void onCaughtFire(BlockState state,
                             Level level,
                             BlockPos pos,
                             @Nullable Direction direction,
                             @Nullable LivingEntity igniter) {

        this.explode(level, pos, igniter);
    }

    @Override
    public boolean canConnectRedstone(BlockState state,
                                      BlockGetter level,
                                      BlockPos pos,
                                      @Nullable Direction direction) {

        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(@NotNull BlockState state,
                                @NotNull Level level,
                                @NotNull BlockPos pos,
                                @NotNull Block block,
                                @NotNull BlockPos neighbor,
                                boolean magic) {

        super.neighborChanged(state, level, pos, block, neighbor, magic);
        this.onPlace(state, level, pos, state, magic);
    }

    @Override
    public void wasExploded(@NotNull Level level,
                            @NotNull BlockPos pos,
                            @NotNull Explosion explosion) {

        this.explode(level, pos, explosion.getExploder());
    }

    @Override
    public int getFireSpreadSpeed(BlockState state,
                                  BlockGetter level,
                                  BlockPos pos,
                                  Direction direction) {

        return 1;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state,
                                        @NotNull BlockGetter getter,
                                        @NotNull BlockPos pos,
                                        @NotNull CollisionContext context) {

        return Stream.of(
                Block.box(14.778174593052023, 0, 4, 15.778174593052023, 16, 12),
                Block.box(0, 0, 4, 1, 16, 12),
                Block.box(3.889087296526011, 0, 14.88908729652601, 11.88908729652601, 16, 15.88908729652601),
                Block.box(3.889087296526013, 0, 0.11091270347398918, 11.889087296526013, 16, 1.1109127034739892),
                Block.box(1, 1, 4, 15, 2, 12),
                Block.box(4, 1, 1, 12, 2, 15),
                Block.box(1, 14, 4, 15, 15, 12),
                Block.box(4, 14, 1, 12, 15, 15)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    }

    private void explode(@NotNull Level level, @NotNull BlockPos pos, Entity entity) {
        if (!level.isClientSide) {
            level.destroyBlock(pos, true, entity);
            level.explode(entity, pos.getX(), pos.getY(), pos.getZ(), EXPLODE_RADIUS, true,
                    Level.ExplosionInteraction.TNT);
        }
    }
}
