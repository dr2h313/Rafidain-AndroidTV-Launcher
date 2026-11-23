package com.gathin.tvhome;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Built-in Theme and Wallpaper management for Rafidain TV Launcher.
 * Bundles Iraqi Holy Shrines & Mesopotamian Civilizations into the APK
 * and provides an intuitive, remote-friendly picker.
 */
public final class ThemeAndWallpaperHelper {

    private static final String PREF_NAME = "launcher_settings";

    public static class WallpaperOption {
        public final String key; // file name or "auto" or "none"
        public final String title;
        public final File file;

        public WallpaperOption(String key, String title, File file) {
            this.key = key;
            this.title = title;
            this.file = file;
        }
    }

    /**
     * Extracts built-in wallpapers from assets into the internal app storage so
     * they are always instantly accessible to any user in the world without internet or SD card.
     */
    public static void ensureBundledWallpapers(Context ctx) {
        new Thread(() -> {
            try {
                File targetDir = new File(ctx.getFilesDir(), "wallpapers");
                if (!targetDir.exists()) targetDir.mkdirs();

                AssetManager am = ctx.getAssets();
                String[] list = am.list("wallpapers");
                if (list != null) {
                    for (String name : list) {
                        File outFile = new File(targetDir, name);
                        if (!outFile.exists() || outFile.length() == 0) {
                            try (InputStream in = am.open("wallpapers/" + name);
                                 OutputStream out = new FileOutputStream(outFile)) {
                                byte[] buf = new byte[8192];
                                int len;
                                while ((len = in.read(buf)) > 0) {
                                    out.write(buf, 0, len);
                                }
                            }
                        }
                    }
                }
            } catch (Exception ignored) {}
        }).start();
    }

    public static List<WallpaperOption> getWallpaperOptions(Context ctx) {
        List<WallpaperOption> list = new ArrayList<>();
        list.add(new WallpaperOption("auto", "🔄 تبديل دوري تلقائي لجميع الخلفيات", null));

        Map<String, File> files = new LinkedHashMap<>();

        // 1. App internal wallpapers dir
        File intDir = new File(ctx.getFilesDir(), "wallpapers");
        scanDir(intDir, files);

        // 2. SDCard Pictures Wallpapers
        File sdDir = new File("/sdcard/Pictures/Wallpapers");
        scanDir(sdDir, files);

        // 3. External app dir
        File extDir = new File("/sdcard/Android/data/com.gathin.tvhome/files/wallpapers");
        scanDir(extDir, files);

        for (Map.Entry<String, File> entry : files.entrySet()) {
            String title = formatTitle(entry.getKey());
            list.add(new WallpaperOption(entry.getKey(), title, entry.getValue()));
        }

        list.add(new WallpaperOption("none", "⚫ خلفية سوداء نقية (شاشات OLED)", null));
        return list;
    }

    private static void scanDir(File dir, Map<String, File> out) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) return;
        File[] files = dir.listFiles((d, n) -> {
            String lower = n.toLowerCase();
            return lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png") || lower.endsWith(".webp");
        });
        if (files == null) return;
        for (File f : files) {
            String name = f.getName();
            if (!out.containsKey(name)) {
                out.put(name, f);
            }
        }
    }

    public static String formatTitle(String fileName) {
        if (fileName == null) return "";
        String clean = fileName.toLowerCase();
        if (clean.contains("najaf") || clean.contains("علي")) {
            return "🏛️ مرقد أمير المؤمنين الإمام علي (ع) - النجف";
        }
        if (clean.contains("karbala") || clean.contains("حرمين") || clean.contains("حسين") || clean.contains("عباس")) {
            return "🕌 بين الحرمين الشريفين - كربلاء المقدسة";
        }
        if (clean.contains("babylon") || clean.contains("ishtar") || clean.contains("عشتار") || clean.contains("بابل")) {
            return "🦁 بوابة عشتار - حضارة بابل العظيمة";
        }
        if (clean.contains("sumer") || clean.contains("ur_") || clean.contains("أور") || clean.contains("سومر")) {
            return "⭐ زقورة أور - حضارة سومر الخالدة";
        }
        if (clean.contains("basra") || clean.contains("shatt") || clean.contains("بصرة") || clean.contains("عرب")) {
            return "🌴 شط العرب وبساتين البصرة الفيحاء";
        }
        if (clean.contains("samarra") || clean.contains("malwiya") || clean.contains("سامراء") || clean.contains("ملوية")) {
            return "🌀 ملوية سامراء - حضارة الرافدين";
        }
        if (clean.contains("holy") || clean.contains("عتبات") || clean.contains("مراقد")) {
            return "🕌 العتبات المقدسة في العراق";
        }
        return "🖼️ " + fileName.replace(".jpg", "").replace(".png", "").replace("_", " ").replace("-", " ");
    }

    public static void showThemePicker(Activity activity, Runnable onApplied) {
        List<WallpaperOption> options = getWallpaperOptions(activity);
        String[] titles = new String[options.size()];
        for (int i = 0; i < options.size(); i++) {
            titles[i] = options.get(i).title;
        }

        SharedPreferences prefs = activity.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String currentChoice = prefs.getString(Config.WALLPAPER_CHOICE_KEY, null);

        int checked = 0; // default "auto"
        if ("none".equals(currentChoice)) {
            checked = options.size() - 1;
        } else if (currentChoice != null) {
            for (int i = 0; i < options.size(); i++) {
                if (currentChoice.equals(options.get(i).key)) {
                    checked = i;
                    break;
                }
            }
        }

        new AlertDialog.Builder(activity)
                .setTitle("🎨 ثيمات وخلفيات المراقد وعراقة الرافدين")
                .setSingleChoiceItems(titles, checked, (dialog, which) -> {
                    dialog.dismiss();
                    WallpaperOption opt = options.get(which);
                    SharedPreferences.Editor ed = prefs.edit();
                    if ("auto".equals(opt.key)) {
                        ed.remove(Config.WALLPAPER_CHOICE_KEY);
                        Toast.makeText(activity, "تم تفعيل التبديل التلقائي للخلفيات 🔄", Toast.LENGTH_SHORT).show();
                    } else if ("none".equals(opt.key)) {
                        ed.putString(Config.WALLPAPER_CHOICE_KEY, "none");
                        Toast.makeText(activity, "تم تطبيق الخلفية السوداء النقية ⚫", Toast.LENGTH_SHORT).show();
                    } else {
                        ed.putString(Config.WALLPAPER_CHOICE_KEY, opt.key);
                        Toast.makeText(activity, "تم تطبيق الثيم بنجاح: " + opt.title + " 🎨", Toast.LENGTH_SHORT).show();
                    }
                    ed.apply();
                    if (onApplied != null) onApplied.run();
                })
                .setNegativeButton("إلغاء", null)
                .show();
    }
}
