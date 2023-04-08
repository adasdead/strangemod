package com.github.strangemod.event;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.item.extra.ItemNoTab;
import com.github.strangemod.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = StrangeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterCreativeTabEvent {
    private static void addItemToTab(CreativeModeTab.ItemDisplayParameters params,
                                     CreativeModeTab.@NotNull Output output) {

        ModItems.ITEMS.getEntries().stream().map(Supplier::get).forEach(item -> {
            if (!item.getClass().isAnnotationPresent(ItemNoTab.class)) {
                output.accept(item);
            }
        });

    }

    @SubscribeEvent
    public static void onRegisterCreativeTab(@NotNull CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(StrangeMod.location("items"), builder ->
                builder.title(Component.translatable("itemGroup.strange_mod"))
                        .icon(() -> ModItems.GUY_FAWKES_MASK.get().getDefaultInstance())
                        .displayItems(RegisterCreativeTabEvent::addItemToTab));
    }
}
