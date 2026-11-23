package com.gathin.tvhome;

import android.service.dreams.DreamService;
import android.widget.TextView;

/**
 * Screensaver: digital clock, date, and Ahlulbayt daily supplication over slow ambient glow.
 * Drifts to a new spot every minute to protect TV panels from OLED burn-in.
 */
public class ClockDream extends DreamService {

    private ClockScene scene;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setInteractive(false);   // any key wakes the TV back to where it was
        setFullscreen(true);
        setScreenBright(false);
        setContentView(R.layout.dream_clock);
        ClockFormat.apply(getWindow().getDecorView(), Config.load(this).clockFormat);

        AhlulbaytWisdomHelper.DailyWisdom wisdom = AhlulbaytWisdomHelper.getTodayWisdom();
        TextView duaTitle = findViewById(R.id.dreamDuaTitle);
        if (duaTitle != null) duaTitle.setText("🕌 " + wisdom.duaTitle);

        TextView duaContent = findViewById(R.id.dreamDuaContent);
        if (duaContent != null) duaContent.setText(wisdom.duaContent);

        TextView hadith = findViewById(R.id.dreamHadith);
        if (hadith != null) hadith.setText(wisdom.hadithText + " (" + wisdom.hadithSource + ")");

        TextView dreamInfo = findViewById(R.id.dreamInfo);
        if (dreamInfo != null) {
            dreamInfo.setText(HomeWidgetHelper.getCachedPrayer() + "  ·  " + HomeWidgetHelper.getCachedWeather() + "  ·  البصرة وشط العرب");
        }

        scene = new ClockScene(getWindow().getDecorView());
        scene.useHomeWallpaper(this);
    }

    @Override
    public void onDreamingStarted() {
        super.onDreamingStarted();
        scene.start();
    }

    @Override
    public void onDreamingStopped() {
        scene.stop();
        super.onDreamingStopped();
    }

    @Override
    public void onDetachedFromWindow() {
        if (scene != null) scene.stop();
        super.onDetachedFromWindow();
    }
}
