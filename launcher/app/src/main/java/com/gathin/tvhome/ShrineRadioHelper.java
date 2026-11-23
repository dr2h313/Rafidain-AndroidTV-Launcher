package com.gathin.tvhome;

import android.app.AlertDialog;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * مشغل إذاعات العتبات المقدسة والقرآن الكريم العراقي:
 * تشغيل البث المباشر لإذاعة الروضة الحسينية، وتلاوات كبار القراء العراقيين (عامر الكاظمي وميثم التمار)
 */
public final class ShrineRadioHelper {

    public static class Station {
        public final String name;
        public final String city;
        public final String url;

        public Station(String name, String city, String url) {
            this.name = name;
            this.city = city;
            this.url = url;
        }
    }

    public static final Station[] STATIONS = {
        new Station("بث مباشر: إذاعة الروضة الحسينية المقدسة", "كربلاء المقدسة", "https://www.imamhussain-fm.com/radio/stream"),
        new Station("بث مباشر: إذاعة التلاوات الخاشعة", "إذاعة القرآن الكريم", "https://backup.qurango.net/radio/salma"),
        new Station("الحاج عامر الكاظمي - سورة الكهف", "تلاوة بالطور والمقام العراقي", "https://archive.org/download/Way2sona_20151221_1450/018.mp3"),
        new Station("الحاج عامر الكاظمي - سورة يس", "تلاوة بالطور والمقام العراقي", "https://archive.org/download/Way2sona_20151221_1450/036.mp3"),
        new Station("الحاج عامر الكاظمي - سورة الرحمن", "تلاوة بالطور والمقام العراقي", "https://archive.org/download/Way2sona_20151221_1450/055.mp3"),
        new Station("الحاج عامر الكاظمي - سورة الملك", "تلاوة بالطور والمقام العراقي", "https://archive.org/download/Way2sona_20151221_1450/067.mp3"),
        new Station("ميثم التمار - سورة الكهف", "تلاوة عراقية خاشعة", "https://archive.org/download/Quran_maythamAlTammar/Quran18.mp3"),
        new Station("ميثم التمار - سورة يس", "تلاوة عراقية خاشعة", "https://archive.org/download/Quran_maythamAlTammar/Quran36.mp3"),
        new Station("ميثم التمار - سورة الرحمن", "تلاوة عراقية خاشعة", "https://archive.org/download/Quran_maythamAlTammar/Quran55.mp3"),
        new Station("ميثم التمار - سورة الواقعة", "تلاوة عراقية خاشعة", "https://archive.org/download/Quran_maythamAlTammar/Quran56.mp3"),
        new Station("ميثم التمار - سورة الملك", "تلاوة عراقية خاشعة", "https://archive.org/download/Quran_maythamAlTammar/Quran67.mp3")
    };

    private static MediaPlayer mediaPlayer = null;
    private static int currentStationIndex = -1;
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    public static String getCurrentStationName() {
        if (currentStationIndex >= 0 && currentStationIndex < STATIONS.length) {
            return STATIONS[currentStationIndex].name;
        }
        return "";
    }

    public static void stop() {
        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.reset();
                mediaPlayer.release();
            } catch (Exception ignored) {}
            mediaPlayer = null;
            currentStationIndex = -1;
        }
    }

    public static void play(Context context, int index) {
        if (index < 0 || index >= STATIONS.length) return;
        stop();
        currentStationIndex = index;
        Station st = STATIONS[index];

        Toast.makeText(context, "📻 جاري تشغيل: " + st.name + " (" + st.city + ")...", Toast.LENGTH_LONG).show();

        new Thread(() -> {
            try {
                MediaPlayer mp = new MediaPlayer();
                mp.setAudioAttributes(new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build());

                Map<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0 (Android TV; Leanback)");
                mp.setDataSource(context, Uri.parse(st.url), headers);

                mp.setOnPreparedListener(player -> {
                    handler.post(() -> {
                        mediaPlayer = player;
                        player.start();
                        Toast.makeText(context, "▶️ يعمل الآن: " + st.name, Toast.LENGTH_SHORT).show();
                    });
                });
                mp.setOnCompletionListener(player -> {
                    handler.post(() -> {
                        stop();
                    });
                });
                mp.setOnErrorListener((player, what, extra) -> {
                    handler.post(() -> {
                        Toast.makeText(context, "⚠️ تعذر تشغيل البث المباشر حالياً", Toast.LENGTH_SHORT).show();
                        stop();
                    });
                    return true;
                });
                mp.prepareAsync();
            } catch (Exception e) {
                handler.post(() -> {
                    Toast.makeText(context, "⚠️ تعذر تشغيل الإذاعة: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    stop();
                });
            }
        }).start();
    }

    /**
     * يفتح قائمة اختيار الإذاعات الشريفة والتلاوات بالريموت
     */
    public static void showRadioPicker(Context context) {
        String[] titles = new String[STATIONS.length + 1];
        for (int i = 0; i < STATIONS.length; i++) {
            titles[i] = (currentStationIndex == i && isPlaying() ? "🔊 " : "📻 ")
                    + STATIONS[i].name + " · " + STATIONS[i].city;
        }
        titles[STATIONS.length] = "⏹️ إيقاف البث الصوتي";

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("🕌 إذاعات العتبات المقدسة والقرآن العراقي");
        builder.setItems(titles, (dialog, which) -> {
            if (which == STATIONS.length) {
                stop();
                Toast.makeText(context, "⏹️ تم إيقاف المشغل الصوتي", Toast.LENGTH_SHORT).show();
            } else {
                play(context, which);
            }
        });
        builder.setNegativeButton("إلغاء", null);
        builder.show();
    }
}
