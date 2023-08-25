package com.strangedevs.strangemod.util;


import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Vectors {
    private Vectors() {
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Vector3d scalar(double value) {
        return new Vector3d(value, value, value);
    }

    @Contract("_ -> new")
    public static @NotNull Vector3d toVec3(@NotNull BlockPos blockPos) {
        return new Vector3d(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull BlockPos toBlockPos(@NotNull Vector3d vec) {
        return new BlockPos((int) vec.x, (int) vec.y, (int) vec.z);
    }
}
