package com.beetw.strangemod;

import com.beetw.strangemod.event.ClientSetupEvent;
import com.beetw.strangemod.event.CommonSetupEvent;
import com.beetw.strangemod.event.EnqueueIMCEvent;
import com.beetw.strangemod.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(StrangeMod.MOD_ID)
public class StrangeMod {
    public static final String MOD_ID = "strange_mod";

    public StrangeMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(CommonSetupEvent::onCommonSetup);
        eventBus.addListener(ClientSetupEvent::onClientSetup);
        eventBus.addListener(EnqueueIMCEvent::onEnqueueIMC);

        ModBlocks.BLOCKS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModRecipes.RECIPES.register(eventBus);
        ModPaintings.PAINTINGS_TYPES.register(eventBus);
        ModEntityTypes.ENTITY_TYPES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}


