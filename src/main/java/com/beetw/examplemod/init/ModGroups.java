package com.beetw.examplemod.init;

import com.beetw.examplemod.ExampleMod;
import com.beetw.examplemod.init.group.ModItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModGroups {
    public static final ItemGroup EXAMPLE_MOD = new ModItemGroup(ExampleMod.MOD_ID,
            () -> new ItemStack(ModBlocks.GUNPOWDER_BARREL.get()));
}