package com.github.strangemod.item.extra;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BaseItem extends Item {
    private final boolean foil;

    private BaseItem(Properties properties, boolean foil) {
        super(properties);

        this.foil = foil;
    }

    @Contract(" -> new")
    public static @NotNull Builder builder() {
        return new Builder();
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return this.foil || itemStack.isEnchanted();
    }

    @SuppressWarnings("unused")
    public static class Builder {
        private Properties properties = new Properties();
        private boolean foil = false;

        public Builder durability(int durability) {
            properties = properties.durability(durability);
            return this;
        }

        public Builder food(FoodProperties food) {
            properties = properties.food(food);
            return this;
        }

        public Builder stacksTo(int count) {
            properties = properties.stacksTo(count);
            return this;
        }

        public Builder foil(boolean value) {
            this.foil = value;
            return this;
        }

        public @Nullable Item build() {
            return new BaseItem(properties, foil);
        }

        public Supplier<Item> supplier() {
            return this::build;
        }
    }
}
