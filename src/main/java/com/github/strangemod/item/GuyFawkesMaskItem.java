package com.github.strangemod.item;

import com.github.strangemod.registry.ModArmorMaterial;
import com.github.strangemod.util.Tooltips;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.world.effect.MobEffects.JUMP;

public class GuyFawkesMaskItem extends ArmorItem {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1).durability(50);

    public GuyFawkesMaskItem() {
        super(ModArmorMaterial.GUY_FAWKES_MASK, Type.HELMET, PROPERTIES);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack itemStack,
                              @NotNull Level level,
                              @NotNull Entity entity,
                              int itemSlot,
                              boolean isSelected) {

        if (Inventory.HELMET_SLOT_ONLY[0] == itemSlot && entity instanceof Player)
            ((Player)entity).addEffect(new MobEffectInstance(JUMP, 10, 5));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level world,
                                @NotNull List<Component> components,
                                @NotNull TooltipFlag flag) {

        Tooltips.appender(components)
                .translate("tooltip.strange_mod.guy_fawkes_mask.0");

        super.appendHoverText(stack, world, components, flag);
    }
}