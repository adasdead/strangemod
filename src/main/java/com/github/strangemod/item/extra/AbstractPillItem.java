package com.github.strangemod.item.extra;

import com.github.strangemod.registry.ModFoods;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public abstract class AbstractPillItem extends Item {
    public AbstractPillItem() {
        super(new Properties().food(ModFoods.PILL));
    }

    public abstract void onEat(Player player);

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack,
                                              @NotNull Level level,
                                              @NotNull LivingEntity entity) {

        if (entity instanceof Player) this.onEat((Player) entity);

        return super.finishUsingItem(itemStack, level, entity);
    }

    // TODO: To be implemented in the future
    public abstract Color getColor();
}
