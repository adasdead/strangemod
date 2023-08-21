package com.strangedevs.strangemod.item;

import com.strangedevs.strangemod.item.extra.ItemTooltipAppender;
import com.strangedevs.strangemod.registry.ModArmorMaterial;
import com.strangedevs.strangemod.registry.ModGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.potion.Effects.JUMP;

public class GuyFawkesMaskItem extends ArmorItem {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .durability(50)
            .tab(ModGroups.EXAMPLE_MOD);

    public GuyFawkesMaskItem() {
        super(ModArmorMaterial.GUY_FAWKES_MASK, EquipmentSlotType.HEAD, PROPERTIES);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, @NotNull PlayerEntity player) {
        player.addEffect(new EffectInstance(JUMP, 10, 5));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable World world,
                                @NotNull List<ITextComponent> components,
                                @NotNull ITooltipFlag flag) {

        ItemTooltipAppender appender = new ItemTooltipAppender(components);
        appender.translate("guy_fawkes_mask.0");
        super.appendHoverText(stack, world, components, flag);
    }
}