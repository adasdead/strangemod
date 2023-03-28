package com.beetw.strangemod.init;

import com.beetw.strangemod.StrangeMod;
import com.beetw.strangemod.item.*;
import com.beetw.strangemod.item.extra.ItemBuilder;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, StrangeMod.MOD_ID);

    public static final RegistryObject<Item> AWAKING_STAFF = ITEMS.register(
            "awaking_staff", AwakingStaffItem::new);

    public static final RegistryObject<Item> STAFF_OF_LIGHTNING = ITEMS.register(
            "staff_of_lightning", StaffOfLightningItem::new);

    public static final RegistryObject<Item> AWAKING_IRON_INGOT = ITEMS.register(
            "awaking_iron_ingot", new ItemBuilder().foil().supplier());

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register(
            "steel_ingot", new ItemBuilder().supplier());

    public static final RegistryObject<Item> CHIPS = ITEMS.register(
            "chips", ChipsItem::new);

    public static final RegistryObject<Item> FUNNY_NOKIA = ITEMS.register(
            "funny_nokia", FunnyNokiaItem::new);

    public static final RegistryObject<Item> AWAKING_SPHERE = ITEMS.register(
            "awaking_sphere", new ItemBuilder().stacksTo(1).supplier());

    public static final RegistryObject<Item> LIGHTBOLT_SPHERE = ITEMS.register(
            "lightbolt_sphere", new ItemBuilder().stacksTo(1).supplier());

    public static final RegistryObject<Item> AWAKENED_PICKAXE = ITEMS.register(
            "awakened_pickaxe", AwakenedPickaxeItem::new);
    public static final RegistryObject<Item> FURHANDS = ITEMS.register(
            "furhands", FurhandsItem::new);
}