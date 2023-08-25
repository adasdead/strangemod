package com.strangedevs.strangemod.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Tooltips {
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Appender appender(@NotNull List<ITextComponent> components) {
        return new Appender(components);
    }

    public static final class Appender {
        private final List<ITextComponent> components;

        private Appender(List<ITextComponent> components) {
            this.components = components;
        }

        public Appender append(String text) {
            components.add(new StringTextComponent(text));
            return this;
        }

        public Appender translate(String translatePath) {
            components.add(new TranslationTextComponent(translatePath));
            return this;
        }

        public Appender empty() {
            components.add(new StringTextComponent(""));
            return this;
        }
    }
}
