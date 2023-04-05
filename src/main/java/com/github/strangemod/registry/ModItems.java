package com.github.strangemod.registry;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.github.strangemod.item.*;
import com.github.strangemod.item.extra.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import static com.github.strangemod.StrangeMod.MOD_ID;

@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
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
            "awaking_sphere", new ItemBuilder().stacksTo(1).foil().supplier());
    public static final RegistryObject<Item> LIGHTBOLT_SPHERE = ITEMS.register(
            "lightbolt_sphere", new ItemBuilder().stacksTo(1).foil().supplier());
    public static final RegistryObject<Item> AWAKENED_PICKAXE = ITEMS.register(
            "awakened_pickaxe", AwakenedPickaxeItem::new);
    public static final RegistryObject<Item> FURHANDS = ITEMS.register(
            "furhands", FurhandsItem::new);
    public static final RegistryObject<Item> GUY_FAWKES_MASK = ITEMS.register(
            "guy_fawkes_mask", GuyFawkesMaskItem::new);



}