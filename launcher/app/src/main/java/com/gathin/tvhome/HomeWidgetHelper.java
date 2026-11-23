package com.gathin.tvhome;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class HomeWidgetHelper {
    private static final String WEATHER_URL = "https://api.open-meteo.com/v1/forecast?latitude=30.8095&longitude=47.6088&current=temperature_2m,weather_code&timezone=auto";
    private static final String PRAYER_URL = "https://api.aladhan.com/v1/timings/%s?latitude=30.8095&longitude=47.6088&method=0";

    private static String cachedWeather = "🌤️ 35° طقس البصرة";
    private static String cachedPrayer = "🕌 أذان المغرب 5:19 م";
    private static long lastFetchTime = 0;
    private static final long CACHE_TTL = 15 * 60 * 1000L; // 15 mins

    public static String getCachedWeather() {
        return cachedWeather != null ? cachedWeather : "🌤️ 35° طقس البصرة";
    }

    public static String getCachedPrayer() {
        return cachedPrayer != null ? cachedPrayer : "🕌 أذان المغرب 5:19 م";
    }

    public static void setup(Context context, View pill, TextView weatherView, TextView prayerView) {
        if (pill == null || weatherView == null || prayerView == null) return;

        pill.setOnClickListener(v -> {
            try {
                Intent it = new Intent();
                it.setComponent(new ComponentName("com.majnioui.tv.prayer", "com.majnioui.tv.prayer.MainActivity"));
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(it);
            } catch (Exception ignored) {}
        });

        weatherView.setOnClickListener(v -> {
            try {
                Intent it = new Intent();
                it.setComponent(new ComponentName("io.github.sumyabazar1.weather", "io.github.sumyabazar1.weather.WeatherActivity"));
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(it);
            } catch (Exception ignored) {}
        });

        prayerView.setOnClickListener(v -> {
            try {
                Intent it = new Intent();
                it.setComponent(new ComponentName("com.majnioui.tv.prayer", "com.majnioui.tv.prayer.MainActivity"));
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(it);
            } catch (Exception ignored) {}
        });

        update(context, weatherView, prayerView);
    }

    public static void update(Context context, TextView weatherView, TextView prayerView) {
        if (weatherView == null || prayerView == null) return;

        long now = System.currentTimeMillis();
        if (cachedWeather != null && cachedPrayer != null && (now - lastFetchTime < CACHE_TTL)) {
            weatherView.setText(cachedWeather);
            prayerView.setText(cachedPrayer);
            return;
        }

        new Thread(() -> {
            try {
                // 1. Weather
                String wData = httpGet(WEATHER_URL);
                JSONObject wJson = new JSONObject(wData).getJSONObject("current");
                int temp = (int) Math.round(wJson.getDouble("temperature_2m"));
                int code = wJson.getInt("weather_code");
                String sky = skyText(code);
                final String wText = "🌤️ " + temp + "° " + sky;

                // 2. Prayer
                String todayStr = new SimpleDateFormat("dd-MM-yyyy", Locale.US).format(new Date());
                String pData = httpGet(String.format(PRAYER_URL, todayStr));
                JSONObject timings = new JSONObject(pData).getJSONObject("data").getJSONObject("timings");

                String[] names = {"Fajr", "Dhuhr", "Asr", "Maghrib", "Isha"};
                String[] arNames = {"الفجر", "الظهر", "العصر", "المغرب", "العشاء"};
                Calendar cal = Calendar.getInstance();
                int currentMins = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);

                String nextPrayerName = arNames[0];
                String nextPrayerTime = timings.getString(names[0]);

                for (int i = 0; i < names.length; i++) {
                    String t = timings.getString(names[i]).trim();
                    int sp = t.indexOf(' ');
                    if (sp > 0) t = t.substring(0, sp);
                    int h = Integer.parseInt(t.substring(0, 2));
                    int m = Integer.parseInt(t.substring(3, 5));
                    if (h * 60 + m > currentMins) {
                        nextPrayerName = arNames[i];
                        nextPrayerTime = format12h(h, m);
                        break;
                    }
                }
                if (nextPrayerTime.contains(":")) {
                    int h = Integer.parseInt(nextPrayerTime.substring(0, 2));
                    int m = Integer.parseInt(nextPrayerTime.substring(3, 5));
                    nextPrayerTime = format12h(h, m);
                }
                final String pText = "🕌 " + nextPrayerName + " " + nextPrayerTime;

                cachedWeather = wText;
                cachedPrayer = pText;
                lastFetchTime = System.currentTimeMillis();

                new Handler(Looper.getMainLooper()).post(() -> {
                    weatherView.setText(wText);
                    prayerView.setText(pText);
                });
            } catch (Exception ignored) {}
        }).start();
    }

    private static String format12h(int h, int m) {
        String p = h >= 12 ? "م" : "ص";
        int h12 = h % 12;
        if (h12 == 0) h12 = 12;
        return String.format(Locale.ROOT, "%d:%02d %s", h12, m, p);
    }

    private static String skyText(int code) {
        if (code == 0) return "صافٍ";
        if (code == 1) return "مشمس";
        if (code == 2) return "غائم جزئياً";
        if (code == 3) return "غائم";
        if (code == 45 || code == 48) return "ضباب";
        if (code >= 51 && code <= 67) return "ممطر";
        if (code >= 95) return "عواصف رعدية";
        return "معتدل";
    }

    private static String httpGet(String urlStr) throws Exception {
        HttpURLConnection c = (HttpURLConnection) new URL(urlStr).openConnection();
        c.setConnectTimeout(6000);
        c.setReadTimeout(6000);
        try (InputStream in = c.getInputStream()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[2048];
            for (int n; (n = in.read(buf)) > 0; ) out.write(buf, 0, n);
            return out.toString("UTF-8");
        } finally {
            c.disconnect();
        }
    }
}
