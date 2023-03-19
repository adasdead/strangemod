package com.beetw.examplemod.item;

import com.beetw.examplemod.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.registries.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final RegistryObject<Item> AWASTAFF = ITEMS.register("awaking_staff",
        () -> new SwordItem(ItemTier.NETHERITE, 2, 3f,
                new Item.Properties().stacksTo(1).tab(ModGroup.getInstance()).durability(100)));

    public static final RegistryObject<Item> AWAINGOT = ITEMS.register("awaking_iron_ingot",
            () -> new Item(new Item.Properties().tab(ModGroup.getInstance())));

    public static final RegistryObject<Item> STEELINGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().tab(ModGroup.getInstance())));
}
