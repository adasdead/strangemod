package com.beetw.strangemod.block;

import com.beetw.strangemod.block.extra.RegisterBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

@RegisterBlockItem
public class GunpowderBarrelBlock extends FallingBlock {
    private static final Properties PROPERTIES = AbstractBlock.Properties
            .of(Material.WOOD)
            .harvestLevel(1)
            .harvestTool(ToolType.AXE)
            .strength(1.0f);

    private static final float EXPLODE_RADIUS = 5.0f;

    public GunpowderBarrelBlock() {
        super(PROPERTIES);

        RenderTypeLookup.setRenderLayer(this, RenderType.cutout());
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull ActionResultType use(@NotNull BlockState blockState,
                                         @NotNull World world,
                                         @NotNull BlockPos pos,
                                         @NotNull PlayerEntity player,
                                         @NotNull Hand hand,
                                         @NotNull BlockRayTraceResult traceResult) {

        ItemStack itemInHand = player.getItemInHand(hand);
        Item item = itemInHand.getItem();

        if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) {
            return super.use(blockState, world, pos, player, hand, traceResult);
        } else {
            this.catchFire(blockState, world, pos, traceResult.getDirection(), player);

            if (!player.isCreative()) {
                if (item == Items.FLINT_AND_STEEL) {
                    itemInHand.hurtAndBreak(1, player,
                            entity -> entity.broadcastBreakEvent(hand));
                } else {
                    itemInHand.shrink(1);
                }
            }

            return ActionResultType.SUCCESS;
        }
    }

    @Override
    public void onPlace(@NotNull BlockState state,
                        @NotNull World world,
                        @NotNull BlockPos pos,
                        @NotNull BlockState state2,
                        boolean magic) {

        if (world.hasNeighborSignal(pos)) {
            this.explode(world, pos, null);
        } else {
            super.onPlace(state, world, pos, state2, magic);
        }
    }

    @Override
    public void onLand(@NotNull World world,
                       @NotNull BlockPos pos,
                       @NotNull BlockState state,
                       @NotNull BlockState state2,
                       @NotNull FallingBlockEntity entity) {

        this.explode(world, pos, null);
    }

    @Override
    public void catchFire(BlockState state,
                          World world,
                          BlockPos pos,
                          @Nullable Direction face,
                          @Nullable LivingEntity igniter) {

        this.explode(world, pos, igniter);
    }

    @Override
    public boolean canConnectRedstone(BlockState state,
                                      IBlockReader world,
                                      BlockPos pos,
                                      @Nullable Direction side) {

        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(@NotNull BlockState state,
                                @NotNull World world,
                                @NotNull BlockPos pos,
                                @NotNull Block block,
                                @NotNull BlockPos neighbor,
                                boolean magic) {

        super.neighborChanged(state, world, pos, block, neighbor, magic);
        this.onPlace(state, world, pos, state, magic);
    }

    @Override
    public void wasExploded(@NotNull World world,
                            @NotNull BlockPos pos,
                            @NotNull Explosion explosion) {

        this.explode(world, pos, explosion.getExploder());
    }

    @Override
    public int getFlammability(BlockState state,
                               IBlockReader world,
                               BlockPos pos,
                               Direction face) {
        return 5;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state,
                                        @NotNull IBlockReader blockReader,
                                        @NotNull BlockPos pos,
                                        @NotNull ISelectionContext context) {

        return Stream.of(
                Block.box(14.778174593052023, 0, 4, 15.778174593052023, 16, 12),
                Block.box(0, 0, 4, 1, 16, 12),
                Block.box(3.889087296526011, 0, 14.88908729652601, 11.88908729652601, 16, 15.88908729652601),
                Block.box(3.889087296526013, 0, 0.11091270347398918, 11.889087296526013, 16, 1.1109127034739892),
                Block.box(1, 1, 4, 15, 2, 12),
                Block.box(4, 1, 1, 12, 2, 15),
                Block.box(1, 14, 4, 15, 15, 12),
                Block.box(4, 14, 1, 12, 15, 15)
        ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    }

    private void explode(@NotNull World world, @NotNull BlockPos pos, Entity entity) {
        if (!world.isClientSide) {
            world.destroyBlock(pos, true, entity);
            world.explode(entity, pos.getX(), pos.getY(), pos.getZ(), EXPLODE_RADIUS, true,
                    Explosion.Mode.DESTROY);
        }
    }
}
