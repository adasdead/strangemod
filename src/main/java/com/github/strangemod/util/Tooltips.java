package com.github.strangemod.util;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Tooltips {
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Appender appender(@NotNull List<Component> components) {
        return new Appender(components);
    }

    public static final class Appender {
        private final List<Component> components;

        private Appender(List<Component> components) {
            this.components = components;
        }

        public Appender append(String text) {
            components.add(Component.literal(text));
            return this;
        }

        public Appender translate(String translatePath) {
            components.add(Component.translatable(translatePath));
            return this;
        }

        public Appender empty() {
            components.add(Component.empty());
            return this;
        }
    }
}
