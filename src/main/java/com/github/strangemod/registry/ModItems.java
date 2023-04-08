package com.github.strangemod.registry;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.item.*;
import com.github.strangemod.item.extra.BaseItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StrangeMod.MOD_ID);

    public static final RegistryObject<Item> AWAKING_STAFF = register("awaking_staff", AwakingStaffItem::new);
    public static final RegistryObject<Item> STAFF_OF_LIGHTNING = register("staff_of_lightning", StaffOfLightningItem::new);
    public static final RegistryObject<Item> AWAKING_IRON_INGOT = register("awaking_iron_ingot", 64, true);
    public static final RegistryObject<Item> STEEL_INGOT = register("steel_ingot");
    public static final RegistryObject<Item> CHIPS = register("chips", ChipsItem::new);
    public static final RegistryObject<Item> FUNNY_NOKIA = register("funny_nokia", FunnyNokiaItem::new);
    public static final RegistryObject<Item> AWAKENED_PICKAXE = register("awakened_pickaxe", AwakenedPickaxeItem::new);
    public static final RegistryObject<Item> FURHANDS = register("furhands", FurhandsItem::new);
    public static final RegistryObject<Item> GUY_FAWKES_MASK = register("guy_fawkes_mask", GuyFawkesMaskItem::new);
    public static final RegistryObject<Item> AWAKING_SPHERE = register("awaking_sphere", 1, true);
    public static final RegistryObject<Item> LIGHTBOLT_SPHERE = register("lightbolt_sphere", 1, true);

    public static @NotNull RegistryObject<Item> register(String id) {
        return register(id, 64);
    }

    public static @NotNull RegistryObject<Item> register(String id, int stacksTo) {
        return register(id, stacksTo, false);
    }

    public static @NotNull RegistryObject<Item> register(String id, int stacksTo, boolean foil) {
        return register(id, BaseItem.builder().stacksTo(stacksTo).foil(foil).supplier());
    }

    public static @NotNull RegistryObject<Item> register(String id, Supplier<Item> supplier) {
        return ITEMS.register(id, supplier);
    }
}