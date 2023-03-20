package com.beetw.examplemod.init;

import com.beetw.examplemod.ExampleMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModGroups {
    public static final ItemGroup EXAMPLE_MOD = new ModItemGroup(ExampleMod.MOD_ID,
            () -> new ItemStack(ModBlocks.GUNPOWDER_BARREL.get()));

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