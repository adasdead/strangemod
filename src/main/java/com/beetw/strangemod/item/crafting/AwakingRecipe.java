package com.beetw.strangemod.item.crafting;

import com.beetw.strangemod.registry.ModItems;
import com.beetw.strangemod.registry.ModRecipes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AwakingRecipe extends SpecialRecipe {
    private static final Ingredient STAFF_INGREDIENT = Ingredient.of(ModItems.AWAKING_STAFF.get());
    private static final Ingredient INGOT_INGREDIENT = Ingredient.of(Items.IRON_INGOT);

    private static final int AMOUNT_OF_DAMAGE = 5;

    public AwakingRecipe(ResourceLocation location) {
        super(location);
    }

    @Override
    public boolean matches(@NotNull CraftingInventory inventory,
                           @NotNull World world) {

        List<ItemStack> stackList = inventoryToList(inventory).stream()
                .filter(itemStack -> !itemStack.isEmpty())
                .collect(Collectors.toList());

        boolean containsIronIngot = stackList.stream().anyMatch(INGOT_INGREDIENT);
        boolean containsStaff = stackList.stream().anyMatch(STAFF_INGREDIENT);

        return (stackList.size() == 2) && containsIronIngot && containsStaff;
    }

    @Override
    @NotNull
    public NonNullList<ItemStack> getRemainingItems(@NotNull CraftingInventory inventory) {
        NonNullList<ItemStack> list = inventoryToList(inventory);

        for (int i = 0; i < list.size(); i++) {
            if (STAFF_INGREDIENT.test(list.get(i))) {
                ItemStack newStaff = list.get(i).copy();
                newStaff.hurt(AMOUNT_OF_DAMAGE, new Random(), null);
                list.set(i, itemIsBroken(newStaff) ? ItemStack.EMPTY : newStaff);
                continue;
            }

            list.set(i, ItemStack.EMPTY);
        }

        return list;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInventory inventory) {
        return new ItemStack(ModItems.AWAKING_IRON_INGOT.get());
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public @NotNull IRecipeSerializer<?> getSerializer() {
        return ModRecipes.AWAKING_STAFF_RECIPE.get();
    }

    @NotNull
    private NonNullList<ItemStack> inventoryToList(@NotNull CraftingInventory inventory) {
        NonNullList<ItemStack> stackList = NonNullList.create();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            stackList.add(i, inventory.getItem(i));
        }

        return stackList;
    }

    private boolean itemIsBroken(@NotNull ItemStack itemStack) {
        return itemStack.getDamageValue() >= itemStack.getMaxDamage();
    }
}
