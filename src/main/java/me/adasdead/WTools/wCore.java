package me.adasdead.WTools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

class wCore {
    private static final String LIB_NAME64 = "WTools64.dll";
    private static final String LIB_NAME32 = "WTools32.dll";

    private static boolean LIB_LOADED = false;

    static void loadLibrary() {
        if (LIB_LOADED) return;

        if (!wSystem.isWindows()) {
            System.err.println("CRITICAL ERROR: wTools works only on windows");
            return;
        }

        if (wSystem.is64()) {
            loadLibraryByName(LIB_NAME64);
        } else {
            loadLibraryByName(LIB_NAME32);
        }
    }

    static void loadLibraryByName(String name) {
        URL url = wCore.class.getResource("/" + name);

        try {
            File tempDir = Files.createTempDirectory("wTools").toFile();
            File tempLib = new File(tempDir, name);

            assert url != null;
            try (InputStream in = url.openStream()) {
                Files.copy(in, tempLib.toPath());
            }

            System.load(tempLib.getAbsolutePath());

            tempDir.deleteOnExit();
            tempLib.deleteOnExit();

            LIB_LOADED = true;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        loadLibrary();
    }
}
