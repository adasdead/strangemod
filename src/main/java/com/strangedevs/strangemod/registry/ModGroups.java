package com.strangedevs.strangemod.registry;

import com.strangedevs.strangemod.StrangeMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModGroups {
    public static final ItemGroup EXAMPLE_MOD = new ModItemGroup(StrangeMod.MOD_ID,
            () -> new ItemStack(ModItems.CHIPS.get()));

    public static class ModItemGroup extends ItemGroup {
        @NotNull
        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(String id, @NotNull Supplier<ItemStack> iconSupplier) {
            super(id);

            this.iconSupplier = iconSupplier;
        }

        @Override
        public @NotNull ItemStack makeIcon() {
            return iconSupplier.get();
        }
    }
}