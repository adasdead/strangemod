package me.adasdead.WTools;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class wMessageBox {
    private static final String BUTTON1_NAME = "OK";
    private static final String BUTTON2_NAME = "Cancel";

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

    public enum Type {
        DEFAULT, PROMPT
    }

    private Type type;

    private String title = "";
    private String text = "";

    private String def = "";

    private String button1 = "";
    private String button2 = "";

    private wMessageBox() {}

    public wMessageBox(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setButton1(String text) {
        this.button1 = text;
    }

    public void setButton2(String text) {
        this.button2 = text;
    }

    public void setDefault(String def) {
        this.def = def;
    }

    public String show() {
        if (type == Type.DEFAULT) {

            if (button2.isEmpty()) {
                rawAlert(text, title, button1);
                return "OK";

            } else {
                if (rawConfirm(text, title, button1, button2) == 0) {
                    return "OK";
                }

                return "Cancel";
            }

        }
        
        return rawPrompt(text, title, def, button1, button2);
    }

    static {
        wCore.loadLibrary();
    }
}
