package com.beetw.strangemod.item;

import com.beetw.strangemod.item.extra.ItemNoTab;
import com.beetw.strangemod.item.extra.ItemTooltipAppender;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@ItemNoTab
public class FurhandsItem extends ShovelItem {

    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1).durability(5000).rarity(Rarity.EPIC);

    public FurhandsItem() {
        super(Tiers.IRON, 2, 3.0f, PROPERTIES);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level world,
                                @NotNull List<Component> components,
                                @NotNull TooltipFlag flag) {

        ItemTooltipAppender appender = new ItemTooltipAppender(components);
        appender.empty_line().translate("furhands.0");
        super.appendHoverText(stack, world, components, flag);
    }
}
