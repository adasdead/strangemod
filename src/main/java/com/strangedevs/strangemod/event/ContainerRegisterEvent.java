package com.strangedevs.strangemod.event;

import com.strangedevs.strangemod.client.screens.ChipsStandScreen;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerRegisterEvent {
    @SubscribeEvent
    public static void onContainerRegistry(final RegistryEvent.@NotNull Register<ContainerType<?>> event) {
        event.getRegistry().register(IForgeContainerType.create(ChipsStandScreen.Menu::new)
                .setRegistryName("chips_stand"));
    }
}
