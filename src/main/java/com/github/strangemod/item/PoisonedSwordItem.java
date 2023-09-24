package com.github.strangemod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

public class PoisonedSwordItem extends SwordItem {
    public PoisonedSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1).durability(5000);

    public PoisonedSwordItem() {
        super(Tiers.NETHERITE, 2, 3.0f, PROPERTIES);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffects.POISON, 100), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
