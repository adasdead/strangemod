package com.github.strangemod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Vectors {
    private Vectors() {
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Vec3 scalar(double value) {
        return new Vec3(value, value, value);
    }

    @Contract("_ -> new")
    public static @NotNull Vec3 toVec3(@NotNull BlockPos blockPos) {
        return new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull BlockPos toBlockPos(@NotNull Vec3 vec) {
        return new BlockPos((int) vec.x, (int) vec.y, (int) vec.z);
    }
}
