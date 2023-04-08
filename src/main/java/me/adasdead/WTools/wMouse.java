package me.adasdead.WTools;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"unused"})
public class wMouse {
    static {
        wCore.loadLibrary();
    }

    private static native void click(short button);

    private static native int[] _getPosition();

    public static native void setPosition(int x, int y);

    public static void click(@NotNull Button button) {
        click((short) button.ordinal());
    }

    public static @NotNull Vec2i getPosition() {
        int[] position = _getPosition();
        return new Vec2i(position[0], position[1]);
    }

    public enum Button {
        RIGHT, LEFT
    }

    static public class Vec2i {
        public final int x;
        public final int y;

        public Vec2i(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
