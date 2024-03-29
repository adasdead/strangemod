package com.github.strangemod;

import com.github.strangemod.event.ClientSetupEvent;
import com.github.strangemod.event.CommonSetupEvent;
import com.github.strangemod.registry.*;
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

        ModBlocks.BLOCKS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModPaintings.PAINTINGS_TYPES.register(eventBus);
        ModEntityTypes.ENTITY_TYPES.register(eventBus);
        ModSoundEvents.SOUND_EVENTS.register(eventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(eventBus);
        ModMenuTypes.MENUS.register(eventBus);
        ModCreativeTabs.CREATIVE_TABS.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static @NotNull ResourceLocation location(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
