package me.adasdead.WTools;

@SuppressWarnings("unused")
public class wNotifications {
    static {
        wCore.loadLibrary();
    }

    public static native void notify(String message, String text);
}
