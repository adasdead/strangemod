package com.github.strangemod.registry;

import com.github.strangemod.StrangeMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {
    GUY_FAWKES_MASK("guy_fawkes_mask", 5, new int[]{1, 2, 3, 1}, 15,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.LEATHER));

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairMaterial;

    ModArmorMaterial(String nameIn,
                     int maxDamageFactorIn,
                     int[] damageReductionAmountsIn,
                     int enchantabilityIn,
                     SoundEvent equipSoundIn,
                     float p_i48533_8_,
                     float knockbackResistIn,
                     Supplier<Ingredient> repairMaterialSupplier) {

        this.name = StrangeMod.location(nameIn).toString();
        this.maxDamageFactor = maxDamageFactorIn;
        this.damageReductionAmountArray = damageReductionAmountsIn;
        this.enchantability = enchantabilityIn;
        this.soundEvent = equipSoundIn;
        this.toughness = p_i48533_8_;
        this.knockbackResistance = knockbackResistIn;
        this.repairMaterial = repairMaterialSupplier;
    }

    @Override
    public int getDurabilityForType(@NotNull ArmorItem.Type type) {
        return MAX_DAMAGE_ARRAY[type.getSlot().getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForType(@NotNull ArmorItem.Type type) {
        return this.damageReductionAmountArray[type.getSlot().getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

}
