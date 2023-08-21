package com.strangedevs.strangemod.item;

import com.strangedevs.strangemod.item.extra.ItemTooltipAppender;
import com.strangedevs.strangemod.registry.ModGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraftforge.common.ToolType.PICKAXE;

public class FurhandsItem extends ShovelItem {

    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .durability(5000)
            .tab(ModGroups.EXAMPLE_MOD)
            .addToolType(PICKAXE, 2)
            .rarity(Rarity.EPIC);

    public FurhandsItem() {
        super(ItemTier.IRON, 2, 3.0f, PROPERTIES);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable World world,
                                @NotNull List<ITextComponent> components,
                                @NotNull ITooltipFlag flag) {

        ItemTooltipAppender appender = new ItemTooltipAppender(components);
        appender.empty_line().translate("furhands.0");
        super.appendHoverText(stack, world, components, flag);
    }
}
