package me.adasdead.WTools;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"DeprecatedIsStillUsed", "unused"})
public class wMouse {
    private wMouse() {}

    private static native void click(short button);
    private static native int[] _getPosition();

    @Deprecated
    public static native void setPosition(int x, int y);
    
    public enum Button {
        LEFT, RIGHT
    }

    public static class Position {
        private int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }

    public static void click(Button btn) {
        if (btn == Button.LEFT) click((short) 1);
        if (btn == Button.RIGHT) click((short) 0);
    }

    public static @NotNull Position getPosition() {
        int[] raw_pos = _getPosition();
        return new Position(raw_pos[0], raw_pos[1]);
    }

    public static void setPosition(@NotNull Position pos) {
        setPosition(pos.x, pos.y);
    }

    static {
        wCore.loadLibrary();
    }
}
