#!/bin/bash
# ======================================================================
#     أداة استرجاع الواجهة الأصلية وإلغاء جميع التعديلات - Linux / Mac / Bash
# ======================================================================

DEFAULT_IP="192.168.88.37:5555"
read -p "أدخل عنوان IP التلفاز [افتراضي: $DEFAULT_IP]: " TV_IP
TV_IP=${TV_IP:-$DEFAULT_IP}

echo "[1/4] 🌐 الاتصال بالتلفاز..."
adb connect $TV_IP

echo "[2/4] 🔄 استرجاع الواجهة الأصلية للنظام..."
adb shell cmd package set-home-activity com.google.android.apps.tv.launcherx/.home.HomeActivity 2>/dev/null
adb shell cmd package set-home-activity com.google.android.tvlauncher/.MainActivity 2>/dev/null
adb shell settings put secure enabled_accessibility_services "" 2>/dev/null

echo "[3/4] 🗑️ إزالة التطبيقات المضافة..."
adb uninstall com.gathin.tvhome 2>/dev/null
adb uninstall com.gathin.shrineradio 2>/dev/null
adb uninstall io.github.sumyabazar1.weather 2>/dev/null
adb uninstall com.majnioui.tv.prayer 2>/dev/null

read -p "هل ترغب أيضاً بإزالة تطبيقات البث (Stremio و CloudStream و SmartTube)؟ [y/N]: " REMOVE_MEDIA
if [[ "$REMOVE_MEDIA" =~ ^[Yy]$ ]]; then
    adb uninstall com.stremio.one 2>/dev/null
    adb uninstall com.lagradost.cloudstream3 2>/dev/null
    adb uninstall org.smarttube.stable 2>/dev/null
fi

echo "[4/4] 🚀 العودة للواجهة الأصلية..."
adb shell input keyevent KEYCODE_HOME
echo "🎉 تم استرجاع التلفاز لوضعه الأصلي بنجاح 100%!"
