package com.github.strangemod.registry;

import com.github.strangemod.StrangeMod;
import com.github.strangemod.item.*;
import com.github.strangemod.item.extra.BaseItem;
import com.github.strangemod.item.pill.FlyyyyyPill;
import com.github.strangemod.util.Durations;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static com.github.strangemod.registry.plugin.RegisterTypes.register;
import static com.github.strangemod.registry.plugin.RegisterTypes.registerRecord;

@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StrangeMod.MOD_ID);
    public static final RegistryObject<Item> STAFF_OF_LIGHTNING = register("staff_of_lightning", StaffOfLightningItem::new);
    public static final RegistryObject<Item> AWAKENED_IRON_INGOT = register("awakened_iron_ingot", 64, true);
    public static final RegistryObject<Item> STEEL_INGOT = register("steel_ingot");
    public static final RegistryObject<Item> CHIPS = register("chips", ChipsItem::new);
    public static final RegistryObject<Item> FUNNY_NOKIA = register("funny_nokia", FunnyNokiaItem::new);
    public static final RegistryObject<Item> AWAKENED_PICKAXE = register("awakened_pickaxe", AwakenedPickaxeItem::new);
    public static final RegistryObject<Item> GUY_FAWKES_MASK = register("guy_fawkes_mask", GuyFawkesMaskItem::new);
    public static final RegistryObject<Item> AWAKING_SPHERE = register("awaking_sphere", 1, true);
    public static final RegistryObject<Item> LIGHTBOLT_SPHERE = register("lightbolt_sphere", 1, true);
    public static final RegistryObject<Item> FORK = register("fork", ForkItem::new);

    public static final RegistryObject<Item> POTATO_KNISH = register("potato_knish", PotatoKnishes::new);
   public static final RegistryObject<Item> STAR_FELL_MUSIC_DISC = registerRecord("star_fell_music_disc",
            ModSoundEvents.STAR_FELL,"1m35s");

    public static final RegistryObject<Item> COKE_COAL = register("coke_coal",
            CoalCoke::new);
    public static final RegistryObject<Item> COOL_BOOK = register("cool_book", 1);

    public static final RegistryObject<Item> POISONED_SWORD = register("poisoned_sword", PoisonedSwordItem::new);

    // public static final RegistryObject<Item> MEGA_FEATHER = register("mega_feather", MegaFeatherItem::new);
    //Steel Items
    public static final RegistryObject<Item> STEEL_ROD = register("steel_rod");
    public static final RegistryObject<Item> STEEL_SCYTH = register("steel_scyth", SteelScythItem::new);

    // Pills
    public static final RegistryObject<Item> FLYYYYY_PILL = register("flyyyyy_pill", FlyyyyyPill::new);

}