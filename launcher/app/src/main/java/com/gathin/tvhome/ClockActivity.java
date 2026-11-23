package com.gathin.tvhome;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * The clock screensaver shown by Home itself after home_idle_clock_minutes without input.
 * Displays the clock, date, and Ahlulbayt daily supplication drifting across the screen.
 * Any key returns to Home.
 */
public class ClockActivity extends Activity {
    @Override
    protected void attachBaseContext(android.content.Context base) {
        super.attachBaseContext(LauncherLocale.wrap(base));
    }

    private ClockScene scene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dream_clock);
        ClockFormat.apply(getWindow().getDecorView(), Config.load(this).clockFormat);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);

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
    protected void onResume() {
        super.onResume();
        scene.start();
    }

    @Override
    protected void onPause() {
        scene.stop();
        super.onPause();
        finish();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) finish();
        return true;
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
