package me.adasdead.WTools;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class wMessageBox {
    static {
        wCore.loadLibrary();
    }

    private static native void rawAlert(String text, String title, String button);

    private static native int rawConfirm(String text, String title, String button1, String button2);

    private static native String rawPrompt(String text, String title, String def, String button1, String button2);

    public static void alert(String text, String title, String button) {
        rawAlert(text, title, button);
    }

    public static int confirm(String text, String title, @NotNull String[] buttons) {
        if (buttons.length != 2) return -1;
        return rawConfirm(text, title, buttons[0], buttons[1]);
    }

    public static String prompt(String text, String title, String def, @NotNull String[] buttons) {
        if (buttons.length != 2) return "";
        return rawPrompt(text, title, def, buttons[0], buttons[1]);
    }
}
