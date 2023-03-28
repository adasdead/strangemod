package com.beetw.strangemod.item.extra;

import com.beetw.strangemod.StrangeMod;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemTooltipAppender {
    private final List<ITextComponent> components;
    private final String modId;

    public ItemTooltipAppender(@NotNull List<ITextComponent> components) {
        this(StrangeMod.MOD_ID, components);
    }

    public ItemTooltipAppender(@NotNull String modId,
                               @NotNull List<ITextComponent> components) {

        this.components = components;
        this.modId = modId;
    }

    public void translate(String path) {
        components.add(new TranslationTextComponent("tooltip." + modId + "." + path));
    }

    public void text(String text) {
        components.add(new StringTextComponent(text));
    }
}
