package com.github.strangemod.item;

import com.github.strangemod.util.Tooltips;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ForkItem extends Item {
    public ForkItem() {
        super(new Item.Properties().stacksTo(1).durability(150));
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack,
                             @NotNull LivingEntity target,
                             @NotNull LivingEntity attacker) {

        if (!(attacker instanceof Player player)) {
            return super.hurtEnemy(stack, target, attacker);
        }

        FoodData foodData = player.getFoodData();

        if (!player.isCreative() && foodData.needsFood()) {
            EquipmentSlot slot = LivingEntity.getEquipmentSlotForItem(stack);
            foodData.setFoodLevel(foodData.getFoodLevel() + 1);
            stack.hurtAndBreak(10, player, entity -> entity.broadcastBreakEvent(slot));
            player.playSound(getEatingSound());
        }

        return true;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level world,
                                @NotNull List<Component> components,
                                @NotNull TooltipFlag flag) {

        Tooltips.appender(components).translate("tooltip.strange_mod.fork");

        super.appendHoverText(stack, world, components, flag);
    }
}
