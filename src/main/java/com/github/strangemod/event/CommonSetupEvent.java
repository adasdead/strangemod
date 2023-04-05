package com.github.strangemod.event;

import com.github.strangemod.network.ModPacketHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetupEvent {
    @SubscribeEvent
    public static void onCommonSetup(final FMLCommonSetupEvent event) {
        ModPacketHandler.register();
    }
}
