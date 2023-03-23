package com.beetw.strangemod.event;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.init.ModItems;
import com.beetw.strangemod.item.ChipsItem;
import com.beetw.strangemod.item.StaffOfLightningItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

public class ClientSetupEvent {
    @SubscribeEvent
    public static void onClientSetup(@NotNull FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemModelsProperties.register(ModItems.STAFF_OF_LIGHTNING.get(),
                new ResourceLocation(StrangeMod.MOD_ID, "shoot"),
                StaffOfLightningItem::itemProperties));

        event.enqueueWork(() -> ItemModelsProperties.register(ModItems.CHIPS.get(),
                new ResourceLocation(StrangeMod.MOD_ID, "eating"),
                ChipsItem::itemProperties));
    }
}
