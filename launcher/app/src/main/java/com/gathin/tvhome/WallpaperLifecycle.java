package com.gathin.tvhome;

/** Decides whether a Home foreground transition should rotate the app's local wallpaper. */
final class WallpaperLifecycle {
    private WallpaperLifecycle() {}

    static boolean shouldRotateOnResume(boolean firstResume, boolean leftForeground, boolean suppressed) {
        return !suppressed && (firstResume || leftForeground);
    }
}
