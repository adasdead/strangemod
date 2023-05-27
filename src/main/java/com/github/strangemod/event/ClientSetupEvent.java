package com.github.strangemod.event;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.client.screens.ChipsStandScreen;
import com.github.strangemod.item.ChipsItem;
import com.github.strangemod.registry.ModItems;
import com.github.strangemod.registry.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class ClientSetupEvent {
    @SubscribeEvent
    public static void onClientSetup(@NotNull FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemProperties.register(ModItems.CHIPS.get(),
                StrangeMod.location("eating"), ChipsItem::itemProperties));

        MenuScreens.register(ModMenuTypes.CHIPS_STAND_MENU.get(), ChipsStandScreen::new);
    }
}
