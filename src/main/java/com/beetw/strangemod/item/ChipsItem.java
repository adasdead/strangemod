package com.beetw.strangemod.item;

import com.beetw.strangemod.registry.ModFoods;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChipsItem extends Item {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1).food(ModFoods.CHIPS).durability(200);

    public ChipsItem() {
        super(PROPERTIES);
    }

    public static float itemProperties(ItemStack itemStack,
                                       @Nullable ClientLevel level,
                                       @Nullable LivingEntity entity,
                                       int time) {

        return entity != null && entity.isUsingItem() &&
                entity.getUseItem() == itemStack ? 1.0f : 0.0f;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack,
                                              @NotNull Level level,
                                              @NotNull LivingEntity entity) {

        ItemStack copyStack = itemStack.copy();

        super.finishUsingItem(itemStack, level, entity);

        if (entity instanceof Player) {
            if (!((Player) entity).isCreative()) {
                copyStack.hurt(10, RandomSource.create(), null);

                if (copyStack.getDamageValue() >= copyStack.getMaxDamage()) {
                    copyStack = ItemStack.EMPTY;
                }
            }
        }

        return copyStack;
    }
}