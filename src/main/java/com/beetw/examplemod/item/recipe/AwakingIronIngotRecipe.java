package com.beetw.examplemod.item.recipe;

import com.beetw.examplemod.item.ModItems;
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

public class AwakingIronIngotRecipe extends SpecialRecipe {
    private static final int AMOUNT_OF_DAMAGE = 5;

    private static final Ingredient STAFF_INGREDIENT = Ingredient.of(ModItems.AWAKING_STAFF.get());
    private static final Ingredient INGOT_INGREDIENT = Ingredient.of(Items.IRON_INGOT);

    public AwakingIronIngotRecipe(ResourceLocation location) {
        super(location);
    }

    @Override
    public boolean matches(@NotNull CraftingInventory inventory,
                           @NotNull World world) {

        List<ItemStack> stackList = invToList(inventory).stream()
                .filter(itemStack -> !itemStack.isEmpty())
                .collect(Collectors.toList());

        boolean containsIronIngot = stackList.stream().anyMatch(INGOT_INGREDIENT);
        boolean containsStaff = stackList.stream().anyMatch(STAFF_INGREDIENT);

        return (stackList.size() == 2) && containsIronIngot && containsStaff;
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
    @NotNull
    public NonNullList<ItemStack> getRemainingItems(@NotNull CraftingInventory inventory) {
        NonNullList<ItemStack> list = invToList(inventory);

        for (int i = 0; i < list.size(); i++) {
            if (STAFF_INGREDIENT.test(list.get(i))) {
                ItemStack newStaff = list.get(i).copy();
                newStaff.hurt(AMOUNT_OF_DAMAGE, new Random(), null);

                if (newStaff.getDamageValue() >= newStaff.getMaxDamage()) {
                    list.set(i, ItemStack.EMPTY);
                } else {
                    list.set(i, newStaff);
                }
            }

            if (INGOT_INGREDIENT.test(list.get(i))) {
                list.set(i, ItemStack.EMPTY);
            }
        }

        return list;
    }

    @Override
    public @NotNull IRecipeSerializer<?> getSerializer() {
        return ModRecipes.AWAKING_STAFF_RECIPE.get();
    }

    @NotNull
    private NonNullList<ItemStack> invToList(@NotNull CraftingInventory inventory) {
        NonNullList<ItemStack> stackList = NonNullList.create();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            stackList.add(i, inventory.getItem(i));
        }

        return stackList;
    }
}
