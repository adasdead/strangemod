package com.beetw.examplemod.item;

import com.beetw.examplemod.ExampleMod;
import com.beetw.examplemod.ModGroup;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);


    public static final RegistryObject<Item> AWASTAFF = ITEMS.register("awaking_staff",
            () -> new Item(new Item.Properties().tab(ModGroup.getInstance())));
}
