package com.beetw.examplemod.item;

import com.beetw.examplemod.init.ModGroups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModItem extends Item {
    private ModItem(Properties properties) {
        super(properties);
    }

    public static class Builder {
        private Item.Properties properties = new Properties();

        public Builder tab(ItemGroup itemGroup) {
            properties = properties.tab(itemGroup);
            return this;
        }

        public Builder durability(int durability) {
            properties = properties.durability(durability);
            return this;
        }

        public Builder food(Food food) {
            properties = properties.food(food);
            return this;
        }

        public ModItem build() {
            return new ModItem(properties);
        }

        public Supplier<ModItem> supplier() {
            return this::build;
        }
    }

    public static class Prepared {
        private final static Builder BUILDER = new Builder()
                .tab(ModGroups.EXAMPLE_MOD);

        public static Supplier<ModItem> supplier() {
            return BUILDER.supplier();
        }
    }
}
