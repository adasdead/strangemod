package com.beetw.examplemod.item;

import com.beetw.examplemod.ExampleMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);


    public static final RegistryObject<Item> AWASTAFF = ITEMS.register("awaking_staff",
        () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
}
