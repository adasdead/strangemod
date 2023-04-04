package com.beetw.strangemod.item;

import com.beetw.strangemod.item.extra.ItemTooltipAppender;
import com.beetw.strangemod.registry.ModArmorMaterial;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
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
    public void onArmorTick(ItemStack stack, Level level, @NotNull Player player) {
        player.addEffect(new MobEffectInstance(JUMP, 10, 5));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level world,
                                @NotNull List<Component> components,
                                @NotNull TooltipFlag flag) {

        ItemTooltipAppender appender = new ItemTooltipAppender(components);
        appender.translate("guy_fawkes_mask.0");
        super.appendHoverText(stack, world, components, flag);
    }
}