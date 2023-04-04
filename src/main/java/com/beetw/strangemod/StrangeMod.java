package com.beetw.strangemod;

import com.beetw.strangemod.event.ClientSetupEvent;
import com.beetw.strangemod.event.CommonSetupEvent;
import com.beetw.strangemod.registry.ModBlocks;
import com.beetw.strangemod.registry.ModEntityTypes;
import com.beetw.strangemod.registry.ModItems;
import com.beetw.strangemod.registry.ModPaintings;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;

@Mod(StrangeMod.MOD_ID)
public class StrangeMod {
    public static final String MOD_ID = "strange_mod";

    public StrangeMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(CommonSetupEvent::onCommonSetup);
        eventBus.addListener(ClientSetupEvent::onClientSetup);

        ModItems.ITEMS.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
        ModPaintings.PAINTINGS_TYPES.register(eventBus);
        ModEntityTypes.ENTITY_TYPES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static @NotNull ResourceLocation location(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}