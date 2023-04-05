package me.adasdead.WTools;

@SuppressWarnings({"DeprecatedIsStillUsed", "unused"})
public class wSystem {
    public static native void reboot();
    public static native void shutdown();
    private static native void _bsod(long code);

    @Deprecated
    public static void bsod(long code) {
        if (isWindows()) _bsod(code);
    }

    public static void bsod() {
        bsod(0xc0000022L);
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static boolean is64() {
        if (System.getProperty("os.name").contains("Windows")) {
            return (System.getenv("ProgramFiles(x86)") != null);
        } else {
            return (System.getProperty("os.arch").contains("64"));
        }
    }

    static {
        wCore.loadLibrary();
    }
}
