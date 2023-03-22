package com.beetw.examplemod.init;

import com.beetw.examplemod.ExampleMod;
import com.beetw.examplemod.item.AwakingStaffItem;
import com.beetw.examplemod.item.ChipsItem;
import com.beetw.examplemod.item.ModItem;
import com.beetw.examplemod.item.StaffOfLightningItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final RegistryObject<Item> AWAKING_STAFF = ITEMS.register(
            "awaking_staff", AwakingStaffItem::new);

    public static final RegistryObject<Item> STAFF_OF_LIGHTNING = ITEMS.register(
            "staff_of_lightning", StaffOfLightningItem::new);

    public static final RegistryObject<Item> AWAKING_IRON_INGOT = ITEMS.register(
            "awaking_iron_ingot", ModItem.Prepared.supplier());

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register(
            "steel_ingot", ModItem.Prepared.supplier());

    public static final RegistryObject<Item> CHIPS = ITEMS.register(
            "chips", ChipsItem::new);
}
