package com.github.strangemod.item.extra;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public interface ItemEmptyClick {
    void onEmptyClick(@NotNull Level world, @NotNull ServerPlayer player);
}
