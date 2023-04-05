package me.adasdead.WTools;

@SuppressWarnings("unused")
public class wNotifications {
    private wNotifications() {}

    public static native void notify(String message, String text);

    static {
        wCore.loadLibrary();
    }
}
