#!/bin/bash
# ======================================================================
#           مـشـروع رافـديـن تـي فـي - Rafidain TV Edition
#       الواجهة التلفزيونية العربية الأسرع والأخف الخالية من الإعلانات
# ======================================================================

DEFAULT_IP="192.168.88.37:5555"
read -p "أدخل عنوان IP التلفاز [افتراضي: $DEFAULT_IP]: " TV_IP
TV_IP=${TV_IP:-$DEFAULT_IP}

echo "[1/6] 🌐 جاري الاتصال بالتلفاز عبر الشبكة ($TV_IP)..."
adb connect $TV_IP

echo "[2/6] 📦 تثبيت اللانشر الرئيسي..."
adb install -r -d "CleanYourTV_HomeLauncher_v1.0.0.apk"

echo "[3/6] 🕌 تثبيت مواقيت الصلاة والأذان (أفق البصرة)..."
adb install -r -d "PrayerTimes_AlNashwa_Basra_v1.0.0.apk"

echo "[4/7] 🌤️ تثبيت حالة الطقس للبصرة والنشوة..."
adb install -r -d "CleanYourTV_Weather_Basra_v1.0.0.apk"

echo "[5/9] 📻 تثبيت إذاعات العتبات والقرآن العراقي..."
adb install -r -d "ShrineRadio_IraqiQuran_v1.0.0.apk"

echo "[6/9] 🎬 تثبيت تطبيقات المشاهدة المجانية (Stremio + CloudStream + SmartTube)..."
[ -f "Stremio_AndroidTV_v1.10.4.apk" ] && adb install -r -d "Stremio_AndroidTV_v1.10.4.apk"
[ -f "CloudStream_TV_v4.8.0.apk" ] && adb install -r -d "CloudStream_TV_v4.8.0.apk"
[ -f "SmartTube_TV_vLatest.apk" ] && adb install -r -d "SmartTube_TV_vLatest.apk"

echo "[7/9] 🖼️ نسخ مكتبة خلفيات العتبات المقدسة ومعالم العراق إلى التلفاز..."
adb shell mkdir -p /sdcard/Android/data/com.gathin.tvhome/files/wallpapers/
adb shell mkdir -p /sdcard/Pictures/Wallpapers/
for img in wallpapers/*.jpg; do
    [ -f "$img" ] && adb push "$img" /sdcard/Pictures/Wallpapers/
    [ -f "$img" ] && adb push "$img" /sdcard/Android/data/com.gathin.tvhome/files/wallpapers/
done

echo "[8/9] ⚙️ ضبط الأذونات واعتراض زر الهوم وتعيين رافدين تي في كواجهة رئيسية..."
adb shell pm grant com.gathin.tvhome android.permission.WRITE_SECURE_SETTINGS
adb shell settings put secure enabled_accessibility_services com.gathin.tvhome/.HomeKeyService
adb shell settings put secure accessibility_enabled 1
adb shell appops set com.gathin.tvhome SYSTEM_ALERT_WINDOW allow
adb shell pm grant com.majnioui.tv.prayer android.permission.RECEIVE_BOOT_COMPLETED
adb shell cmd package set-home-activity com.gathin.tvhome/.MainActivity

echo "[9/9] 🚀 فتح الشاشة الرئيسية الجديدة..."
adb shell input keyevent KEYCODE_HOME
echo "🎉 اكتمل التثبيت بنجاح!"
