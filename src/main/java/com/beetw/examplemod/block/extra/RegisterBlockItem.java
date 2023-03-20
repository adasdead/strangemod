package com.beetw.examplemod.block.extra;

import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterBlockItem {
    interface Properties {
        @NotNull
        default Item.Properties getBlockItemProperties() {
            return new Item.Properties();
        }
    }
}
