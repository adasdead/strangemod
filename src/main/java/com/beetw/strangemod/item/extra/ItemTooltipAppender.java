package com.beetw.strangemod.item.extra;

import com.beetw.strangemod.StrangeMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemTooltipAppender {
    private final List<Component> components;
    private final String modId;
    private final boolean shift;

    public ItemTooltipAppender(@NotNull List<Component> components) {
        this(components, false);
    }

    public ItemTooltipAppender(@NotNull List<Component> components,
                               boolean shift) {

        this(StrangeMod.MOD_ID, components, shift);
    }

    public ItemTooltipAppender(@NotNull String modId,
                               @NotNull List<Component> components,
                               boolean shift) {

        this.components = components;
        this.modId = modId;
        this.shift = shift;

        if (shift && !Screen.hasShiftDown()) {
            components.add(translatable("shift"));
        }
    }

    public ItemTooltipAppender translate(String path) {
        if (!Screen.hasShiftDown() && shift) return this;
        components.add(translatable(path));
        return this;
    }

    public ItemTooltipAppender text(String text) {
        if (!Screen.hasShiftDown() && shift) return this;
        components.add(Component.literal(text));
        return this;
    }

    public ItemTooltipAppender empty_line() {
        return text("");
    }

    @Contract(value = "_ -> new", pure = true)
    private @NotNull Component translatable(String path) {
        return Component.translatable("tooltip." + modId + "." + path);
    }
}
