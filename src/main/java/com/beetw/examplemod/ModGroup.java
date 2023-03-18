package com.beetw.examplemod;

import com.beetw.examplemod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModGroup extends ItemGroup {
    private static ItemGroup instance;

    private ModGroup(int groupCount, String name) {
        super(groupCount, name);
    }

    public static ItemGroup getInstance() {
        if (instance == null) {
            int count = ItemGroup.getGroupCountSafe();
            instance = new ModGroup(count, ExampleMod.MOD_ID);
        }

        return instance;
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(ModBlocks.EXAMPLE_BLOCK.get());
    }
}
