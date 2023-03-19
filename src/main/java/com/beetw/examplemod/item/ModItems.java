package com.beetw.examplemod.item;

import com.beetw.examplemod.ExampleMod;
import com.beetw.examplemod.ModGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final RegistryObject<Item> AWASTAFF = ITEMS.register("awaking_staff",
        () -> new SwordItem(ItemTier.NETHERITE, 2, 3f,
                new Item.Properties().stacksTo(1).tab(ModGroup.getInstance())));

    public static final RegistryObject<Item> AWAINGOT = ITEMS.register("awaking_iron_ingot",
            () -> new Item(new Item.Properties().tab(ModGroup.getInstance())));
}
