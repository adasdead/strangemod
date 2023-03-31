package com.beetw.strangemod.item.extra;

import com.beetw.strangemod.registry.ModGroups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SimpleFoiledItem;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ItemBuilder {
    private Item.Properties properties = new Item.Properties()
            .tab(ModGroups.EXAMPLE_MOD);

    private boolean foil = false;

    public ItemBuilder tab(ItemGroup itemGroup) {
        properties = properties.tab(itemGroup);
        return this;
    }

    public ItemBuilder durability(int durability) {
        properties = properties.durability(durability);
        return this;
    }

    public ItemBuilder food(Food food) {
        properties = properties.food(food);
        return this;
    }

    public ItemBuilder stacksTo(int count) {
        properties = properties.stacksTo(count);
        return this;
    }

    public ItemBuilder foil() {
        this.foil = true;
        return this;
    }

    public @Nullable Item build() {
        if (foil) {
            return new SimpleFoiledItem(properties);
        }

        return new Item(properties);
    }

    public Supplier<Item> supplier() {
        return this::build;
    }
}
