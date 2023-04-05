package com.github.strangemod.block;

import com.github.strangemod.block.extra.RegisterBlockItem;
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

@RegisterBlockItem(registryName = "wood_casing")
public class WoodCasingBlock extends HorizontalDirectionalBlock {
    private static final Block.Properties PROPERTIES = Block.Properties
            .of(Material.WOOD)
            .lightLevel((state) -> 15);

    public WoodCasingBlock() {
        super(PROPERTIES);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state,
                                        @NotNull BlockGetter getter,
                                        @NotNull BlockPos pos,
                                        @NotNull CollisionContext context) {
        return Stream.of(
                Block.box(4.5, 4.5, 4.5, 11.5, 11.5, 11.5),
                Block.box(12, 4, 0, 16, 12, 4),
                Block.box(0, 4, 12, 4, 12, 16),
                Block.box(12, 4, 12, 16, 12, 16),
                Block.box(0, 4, 0, 4, 12, 4),
                Block.box(0, 12, 0, 16, 16, 16),
                Block.box(0, 0, 0, 16, 4, 16)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    }
}

