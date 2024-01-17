package com.github.strangemod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;




public class SteelScythItem extends SwordItem {
    public SteelScythItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }
    public SteelScythItem() {
        super(Tiers.NETHERITE, 14, 3.0f, PROPERTIES);
    }

    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1).durability(5600);
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 10), pAttacker);
        pAttacker.addEffect(new MobEffectInstance(MobEffects.HEAL, 1), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);


    }
}

