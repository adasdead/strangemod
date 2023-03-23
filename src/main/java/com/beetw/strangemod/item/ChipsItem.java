package com.beetw.strangemod.item;

import com.beetw.strangemod.init.ModFoods;
import com.beetw.strangemod.init.ModGroups;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;
import java.util.Random;

import static net.minecraft.potion.Effects.SATURATION;

public class ChipsItem extends Item implements ICurioItem {
    private static final Item.Properties PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .food(ModFoods.CHIPS)
            .durability(200)
            .tab(ModGroups.EXAMPLE_MOD);
    public ChipsItem() {
        super(PROPERTIES);
    }

    public static float itemProperties(@NotNull ItemStack itemStack,
                                       @Nullable ClientWorld world,
                                       @Nullable LivingEntity entity) {


        return entity != null && entity.isUsingItem() &&
                entity.getUseItem() == itemStack ? 1.0f : 0.0f;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack,
                                              @NotNull World world,
                                              @NotNull LivingEntity entity) {

        ItemStack copyStack = itemStack.copy();

        super.finishUsingItem(itemStack, world, entity);

        if (entity instanceof PlayerEntity) {
            if (!((PlayerEntity) entity).isCreative()) {
                copyStack.hurt(10, new Random(), null);

                if (copyStack.getDamageValue() >= copyStack.getMaxDamage()) {
                    copyStack = ItemStack.EMPTY;
                }
            }
        }

        return copyStack;
    }
    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        PlayerEntity player = (PlayerEntity) livingEntity;

        if(!player.level.isClientSide) {
            boolean hasPlayerFireResistance =
                    !Objects.equals(player.getEffect(SATURATION), null);

            if(!hasPlayerFireResistance) {
                player.addEffect(new EffectInstance(SATURATION, 200));

                if(random.nextFloat() > 0.6f) {
                    stack.hurtAndBreak(1, player, p -> CuriosApi.getCuriosHelper().onBrokenCurio(
                            SlotTypePreset.HANDS.getIdentifier(), index, p));
                }
            }
        }

        ICurioItem.super.curioTick(identifier, index, livingEntity, stack);
    }
}

