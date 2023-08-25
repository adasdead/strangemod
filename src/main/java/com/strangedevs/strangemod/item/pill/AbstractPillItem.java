package com.strangedevs.strangemod.item.pill;

import com.strangedevs.strangemod.registry.ModFoods;
import com.strangedevs.strangemod.registry.ModGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public abstract class AbstractPillItem extends Item {
    public AbstractPillItem() {
        super(new Properties().food(ModFoods.PILL).tab(ModGroups.EXAMPLE_MOD));
    }

    public abstract void onEat(PlayerEntity player);

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack,
                                              @NotNull World world,
                                              @NotNull LivingEntity entity) {

        if (entity instanceof PlayerEntity) this.onEat((PlayerEntity) entity);

        return super.finishUsingItem(itemStack, world, entity);
    }

    // TODO: To be implemented in the future
    public abstract Color getColor();
}
