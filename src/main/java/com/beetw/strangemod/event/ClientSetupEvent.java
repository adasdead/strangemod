package com.beetw.strangemod.event;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.client.renderer.entity.NokiaBoxRenderer;
import com.beetw.strangemod.item.ChipsItem;
import com.beetw.strangemod.registry.ModEntityTypes;
import com.beetw.strangemod.registry.ModItems;
import net.minecraft.item.ItemModelsProperties;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

public class ClientSetupEvent {
    @SubscribeEvent
    public static void onClientSetup(@NotNull FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemModelsProperties.register(ModItems.CHIPS.get(),
                StrangeMod.location("eating"), ChipsItem::itemProperties));

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.NOKIA_BOX.get(), NokiaBoxRenderer::new);
    }
}
