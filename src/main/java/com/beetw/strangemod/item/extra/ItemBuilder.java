package com.beetw.strangemod.item.extra;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SimpleFoiledItem;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ItemBuilder {
    private Item.Properties properties = new Item.Properties();

    private boolean foil = false;

    public ItemBuilder durability(int durability) {
        properties = properties.durability(durability);
        return this;
    }

    public ItemBuilder food(FoodProperties food) {
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
