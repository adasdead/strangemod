package me.adasdead.WTools;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.Map;

@SuppressWarnings({"unused"})
public class wMouse {
    private wMouse() {}

    private static native void click(short button);
    private static native int[] _getPosition();

    public static native void setPosition(int x, int y);
    
    public enum Button {
        RIGHT, LEFT
    }

    public static void click(@NotNull Button button) {
        click((short) button.ordinal());
    }

    /**
     * @return {@link Map.Entry}, where: key = x / value = y
     */
    public static @NotNull Map.Entry<Integer, Integer> getPosition() {
        return new AbstractMap.SimpleEntry<>(_getPosition()[0], _getPosition()[1]);
    }

    static {
        wCore.loadLibrary();
    }
}
