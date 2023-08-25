package com.strangedevs.strangemod.registry;

import com.strangedevs.strangemod.StrangeMod;
import com.strangedevs.strangemod.client.screens.ChipsStandScreen;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

public class ModMenuTypes {
    @ObjectHolder(StrangeMod.MOD_ID + ":chips_stand")
    public static ContainerType<ChipsStandScreen.Menu> CHIPS_STAND_MENU;
}
