package com.strangedevs.strangemod.item.extra;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public interface ItemEmptyClick {
    void onEmptyClick(@NotNull World world, @NotNull PlayerEntity playerEntity);
}
