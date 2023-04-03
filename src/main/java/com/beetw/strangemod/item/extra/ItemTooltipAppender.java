package com.beetw.strangemod.item.extra;

import com.beetw.strangemod.StrangeMod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemTooltipAppender {
    private final List<ITextComponent> components;
    private final String modId;
    private final boolean shift;

    public ItemTooltipAppender(@NotNull List<ITextComponent> components) {
        this(components, false);
    }

    public ItemTooltipAppender(@NotNull List<ITextComponent> components,
                               boolean shift) {

        this(StrangeMod.MOD_ID, components, shift);
    }

    public ItemTooltipAppender(@NotNull String modId,
                               @NotNull List<ITextComponent> components,
                               boolean shift) {

        this.components = components;
        this.modId = modId;
        this.shift = shift;

        if (shift && !Screen.hasShiftDown()) {
            components.add(translateComponent("shift"));
        }
    }

    public ItemTooltipAppender translate(String path) {
        if (!Screen.hasShiftDown() && shift) return this;
        components.add(translateComponent(path));
        return this;
    }

    public ItemTooltipAppender text(String text) {
        if (!Screen.hasShiftDown() && shift) return this;
        components.add(new StringTextComponent(text));
        return this;
    }

    public ItemTooltipAppender empty_line() {
        return text("");
    }

    @Contract(value = "_ -> new", pure = true)
    private @NotNull ITextComponent translateComponent(String path) {
        return new TranslationTextComponent("tooltip." + modId + "." + path);
    }
}
