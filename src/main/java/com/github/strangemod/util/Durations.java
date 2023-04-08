package com.github.strangemod.util;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Durations {
    private static final Pattern DURATION_PATTERN;

    static {
        DURATION_PATTERN = Pattern.compile("(\\d+)([msh]s?)");
    }

    private Durations() {
    }

    @SuppressWarnings("ReassignedVariable")
    public static long toMilliseconds(@NotNull String timeString) {
        Matcher matcher = Durations.DURATION_PATTERN.matcher(timeString);
        long result = 0L;

        while (matcher.find()) {
            final long time = Integer.parseInt(matcher.group(1));
            final String group = matcher.group(2);
            switch (group) {
                case "ms" -> result += time;
                case "s" -> result += TimeUnit.SECONDS.toMillis(time);
                case "m" -> result += TimeUnit.MINUTES.toMillis(time);
                case "h" -> result += TimeUnit.HOURS.toMillis(time);
            }
        }

        return result;
    }

    public static int toTicks(@NotNull String timeString) {
        return (int) (TimeUnit.MILLISECONDS.toSeconds(toMilliseconds(timeString)) * 20);
    }
}
