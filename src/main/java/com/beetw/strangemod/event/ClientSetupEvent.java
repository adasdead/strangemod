package com.beetw.strangemod.event;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.item.ChipsItem;
import com.beetw.strangemod.registry.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

public class ClientSetupEvent {
    @SubscribeEvent
    public static void onClientSetup(@NotNull FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemProperties.register(ModItems.CHIPS.get(),
                StrangeMod.location("eating"), ChipsItem::itemProperties));
    }
}
