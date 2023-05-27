package com.github.strangemod.registry;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.client.screens.ChipsStandScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, StrangeMod.MOD_ID);

    public static final RegistryObject<MenuType<ChipsStandScreen.Menu>> CHIPS_STAND_MENU =
            register(ChipsStandScreen.Menu::new, "chips_stand_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(
            IContainerFactory<T> factory, @NotNull String name
    ) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
}
