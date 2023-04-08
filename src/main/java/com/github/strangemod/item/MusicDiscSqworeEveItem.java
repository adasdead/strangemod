package com.github.strangemod.item;

import com.github.strangemod.registry.ModSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;

import java.util.function.Supplier;

public class MusicDiscSqworeEveItem extends RecordItem {

    private static final RecordItem PROPERTIES =

            new RecordItem(15, ModSoundEvents.FALLING_STAR, (new Properties()).stacksTo(1).rarity(Rarity.RARE), 1920);


    public MusicDiscSqworeEveItem(int comparatorValue, Supplier<SoundEvent> soundSupplier, Properties builder, int lengthInTicks) {
        super(comparatorValue, soundSupplier, builder, lengthInTicks);
    }




    }


