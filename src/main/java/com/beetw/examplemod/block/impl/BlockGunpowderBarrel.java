package com.beetw.examplemod.block.impl;

import com.beetw.examplemod.block.ModBlock;
import com.beetw.examplemod.block.ModBlockItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

@ModBlockItem
public class BlockGunpowderBarrel extends ModBlock {
    private static final float EXPLODE_RADIUS = 20.0f;

    public BlockGunpowderBarrel() {
        super("gunpowder_barrel");
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

    @SuppressWarnings("deprecation")
    @Override
    public void attack(@NotNull BlockState state,
                       @NotNull World world,
                       @NotNull BlockPos pos,
                       @NotNull PlayerEntity entity) {

        explode(world, pos, entity);

        super.attack(state, world, pos, entity);
    }

    private void explode(@NotNull World world, @NotNull BlockPos pos, Entity entity) {
        world.explode(entity, pos.getX(), pos.getY(), pos.getZ(), EXPLODE_RADIUS,
                Explosion.Mode.BREAK);
    }
}
