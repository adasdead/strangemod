package com.beetw.examplemod;

import com.beetw.examplemod.init.ModBlocks;
import com.beetw.examplemod.init.ModItems;
import com.beetw.examplemod.init.ModPaintings;
import com.beetw.examplemod.init.ModRecipes;
import com.beetw.examplemod.network.ModPacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExampleMod.MOD_ID)
public class ExampleMod {
    public static final String MOD_ID = "examplemod";

    public ExampleMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setup);

        ModBlocks.BLOCKS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModRecipes.RECIPES.register(eventBus);
        ModPaintings.PAINTINGS_TYPES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModPacketHandler.register();
    }
}