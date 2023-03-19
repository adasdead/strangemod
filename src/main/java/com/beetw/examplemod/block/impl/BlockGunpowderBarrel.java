package com.beetw.examplemod.block.impl;

import com.beetw.examplemod.block.ModBlock;
import com.beetw.examplemod.block.ModBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
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
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

@ModBlockItem
public class BlockGunpowderBarrel extends ModBlock {
    private static final float EXPLODE_RADIUS = 5.0f;

    public BlockGunpowderBarrel() {
        super("gunpowder_barrel", AbstractBlock.Properties.of(Material.WOOD));
        RenderTypeLookup.setRenderLayer(this, RenderType.cutout());
    }

    @Override
    public void stepOn(@NotNull World world, @NotNull BlockPos pos, @NotNull Entity entity) {
        explode(world, pos, entity);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull ActionResultType use(@NotNull BlockState state,
                                         @NotNull World world,
                                         @NotNull BlockPos pos,
                                         @NotNull PlayerEntity entity,
                                         @NotNull Hand hand,
                                         @NotNull BlockRayTraceResult rayTraceResult) {

        explode(world, pos, entity);

        return super.use(state, world, pos, entity, hand, rayTraceResult);
    }

    @Override
    public void wasExploded(@NotNull World world, @NotNull BlockPos pos, @NotNull Explosion explosion) {
        explode(world, pos, explosion.getExploder());
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
        world.destroyBlock(pos, true, entity);
        world.explode(entity, pos.getX(), pos.getY(), pos.getZ(), EXPLODE_RADIUS, true,
                Explosion.Mode.DESTROY);
    }
}
