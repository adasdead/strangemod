package com.beetw.examplemod.init.group;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModItemGroup extends ItemGroup {
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
