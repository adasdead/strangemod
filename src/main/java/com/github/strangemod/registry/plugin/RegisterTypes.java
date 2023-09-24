package com.github.strangemod.registry.plugin;

import com.github.strangemod.item.extra.BaseItem;
import com.github.strangemod.registry.ModItems;
import com.github.strangemod.util.Durations;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

import static com.github.strangemod.registry.ModBlocks.BLOCKS;
import static com.github.strangemod.registry.ModItems.ITEMS;

public class RegisterTypes {
    public static @NotNull RegistryObject<Item> register(String id) {
        return register(id, 64);
    }

    public static @NotNull RegistryObject<Item> register(String id, int stacksTo) {
        return register(id, stacksTo, false);
    }

    public static @NotNull RegistryObject<Item> register(String id, int stacksTo, boolean foil) {
        return register(id, BaseItem.builder().stacksTo(stacksTo).foil(foil).supplier());
    }

    public static @NotNull RegistryObject<Item> registerRecord(String id,
                                                               Supplier<SoundEvent> sound,
                                                               String duration) {

        Item.Properties properties = new Item.Properties().stacksTo(1).rarity(Rarity.RARE);
        return register(id, () -> new RecordItem(15, sound, properties, Durations.toTicks(duration)));
    }

    public static @NotNull RegistryObject<Item> register(String id, Supplier<Item> supplier) {
        return ITEMS.register(id, supplier);
    }
}

