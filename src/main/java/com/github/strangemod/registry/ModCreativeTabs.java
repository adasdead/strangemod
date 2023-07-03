package com.github.strangemod.registry;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.item.extra.ItemNoTab;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static DeferredRegister<CreativeModeTab> CREATIVE_TABS
            = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StrangeMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_TABS
            .register("main_tab", () -> CreativeModeTab.builder()
                    .icon(() -> ModItems.GUY_FAWKES_MASK.get().getDefaultInstance())
                    .displayItems(ModCreativeTabs::addItemToTab)
                    .title(Component.translatable("itemGroup.strange_mod"))
                    .build());

    private static void addItemToTab(CreativeModeTab.ItemDisplayParameters params,
                                     CreativeModeTab.@NotNull Output output) {

        ModItems.ITEMS.getEntries().stream().map(Supplier::get).forEach(item -> {
            if (!item.getClass().isAnnotationPresent(ItemNoTab.class)) {
                output.accept(item);
            }
        });
    }
}
