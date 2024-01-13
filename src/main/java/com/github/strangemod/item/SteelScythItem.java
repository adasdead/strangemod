package com.github.strangemod.item;

import com.github.strangemod.item.extra.ItemEmptyClick;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import net.minecraftforge.event.entity.EntityTeleportEvent;

public class SteelScythItem extends SwordItem  implements ItemEmptyClick {
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
        pTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 100), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void onEmptyClick(@NotNull Level world, @NotNull ServerPlayer player) {


    }
}
