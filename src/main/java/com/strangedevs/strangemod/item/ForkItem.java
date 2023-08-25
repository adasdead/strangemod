package com.strangedevs.strangemod.item;

import com.strangedevs.strangemod.registry.ModGroups;
import com.strangedevs.strangemod.util.Tooltips;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ForkItem extends Item {
    public ForkItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .durability(150)
                .tab(ModGroups.EXAMPLE_MOD));
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack,
                             @NotNull LivingEntity target,
                             @NotNull LivingEntity attacker) {

        if (!(attacker instanceof PlayerEntity)) {
            return super.hurtEnemy(stack, target, attacker);
        }

        PlayerEntity player = (PlayerEntity) attacker;
        FoodStats foodData = player.getFoodData();

        if (!player.isCreative() && foodData.needsFood()) {

            Hand hand = attacker.getUsedItemHand();
            foodData.setFoodLevel(foodData.getFoodLevel() + 1);
            stack.hurtAndBreak(10, player, entity -> entity.broadcastBreakEvent(hand));
            player.playSound(getEatingSound(), 1.0f, 1.0f);
        }

        return true;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable World world,
                                @NotNull List<ITextComponent> components,
                                @NotNull ITooltipFlag flag) {

        Tooltips.appender(components).translate("tooltip.strange_mod.fork");

        super.appendHoverText(stack, world, components, flag);
    }
}
