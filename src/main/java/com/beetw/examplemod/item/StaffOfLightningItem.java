package com.beetw.examplemod.item;

import com.beetw.examplemod.init.ModGroups;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class StaffOfLightningItem extends ToolItem {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .durability(100)
            .tab(ModGroups.EXAMPLE_MOD);

    public StaffOfLightningItem() {
        super(2, 3.0f, ItemTier.IRON, new HashSet<>(), PROPERTIES);
    }

    @Override
    public @NotNull ActionResult<ItemStack> use(@NotNull World world,
                                                @NotNull PlayerEntity entity,
                                                @NotNull Hand hand) {



        return ActionResult.pass(entity.getItemInHand(hand));
    }


}
