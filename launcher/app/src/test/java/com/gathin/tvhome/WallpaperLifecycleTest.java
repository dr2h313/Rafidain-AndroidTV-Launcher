package com.gathin.tvhome;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WallpaperLifecycleTest {
    @Test
    public void rotatesOnColdStartAndAfterLeavingHome() {
        assertTrue(WallpaperLifecycle.shouldRotateOnResume(true, false, false));
        assertTrue(WallpaperLifecycle.shouldRotateOnResume(false, true, false));
    }

    @Test
    public void doesNotRotateForSettingsOrRedrawOnlyResume() {
        assertFalse(WallpaperLifecycle.shouldRotateOnResume(false, false, false));
        assertFalse(WallpaperLifecycle.shouldRotateOnResume(true, true, true));
        assertFalse(WallpaperLifecycle.shouldRotateOnResume(false, true, true));
    }
}
