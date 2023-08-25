package com.strangedevs.strangemod.registry;

import com.strangedevs.strangemod.StrangeMod;
import com.strangedevs.strangemod.client.screens.ChipsStandScreen;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModMenuTypes {
    public static DeferredRegister<ContainerType<?>> CONTAINER_TYPES
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, StrangeMod.MOD_ID);

    public static final RegistryObject<ContainerType<ChipsStandScreen.Menu>> CHIPS_STAND_MENU
            = CONTAINER_TYPES.register("chips_stand", () -> IForgeContainerType.create(ChipsStandScreen.Menu::new));
}
