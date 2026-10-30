@echo off
chcp 65001 >nul
color 0A
title Rafidain TV - مشـروع رافـدين تـي فـي (العراق)

echo ======================================================================
echo           مـشـروع رافـديـن تـي فـي - Rafidain TV Edition
echo       الواجهة التلفزيونية العربية الأسرع والأخف الخالية من الإعلانات
echo         مواقيت الصلاة حسب فقه أهل البيت (ع) + طقس شط العرب والبصرة
echo ======================================================================
echo.

set DEFAULT_IP=192.168.88.37:5555
set /p TV_IP="أدخل عنوان IP التلفاز (اضغط Enter للافتراضي %DEFAULT_IP%): "
if "%TV_IP%"=="" set TV_IP=%DEFAULT_IP%

echo.
echo [1/6] 🌐 جاري الاتصال بالتلفاز عبر الشبكة (%TV_IP%)...
adb connect %TV_IP%
timeout /t 2 >nul

echo.
echo [2/6] 📦 جاري تثبيت الواجهة الرئيسية (Rafidain Home Launcher)...
if exist "CleanYourTV_HomeLauncher_v1.0.0.apk" (
    adb install -r -d "CleanYourTV_HomeLauncher_v1.0.0.apk"
) else (
    echo [!] لم يتم العثور على ملف اللانشر في المجلد الحالي.
)

echo.
echo [3/6] 🕌 جاري تثبيت تطبيق مواقيت الصلاة والأذان (أفق البصرة وشط العرب)...
if exist "PrayerTimes_AlNashwa_Basra_v1.0.0.apk" (
    adb install -r -d "PrayerTimes_AlNashwa_Basra_v1.0.0.apk"
) else (
    echo [!] لم يتم العثور على ملف مواقيت الصلاة في المجلد الحالي.
)

echo.
echo [4/7] 🌤️ جاري تثبيت تطبيق حالة الطقس المفصل للبصرة والنشوة...
if exist "CleanYourTV_Weather_Basra_v1.0.0.apk" (
    adb install -r -d "CleanYourTV_Weather_Basra_v1.0.0.apk"
) else (
    echo [!] لم يتم العثور على ملف الطقس في المجلد الحالي.
)

echo.
echo [5/9] 📻 جاري تثبيت تطبيق إذاعات العتبات المقدسة والقرآن العراقي...
if exist "ShrineRadio_IraqiQuran_v1.0.0.apk" (
    adb install -r -d "ShrineRadio_IraqiQuran_v1.0.0.apk"
) else (
    echo [!] لم يتم العثور على ملف إذاعات العتبات في المجلد الحالي.
)

echo.
echo [6/9] 🎬 جاري تثبيت تطبيقات المشاهدة المجانية (Stremio + CloudStream + SmartTube)...
if exist "Stremio_AndroidTV_v1.10.4.apk" (
    echo     تثبيت Stremio TV 4K...
    adb install -r -d "Stremio_AndroidTV_v1.10.4.apk" >nul 2>&1
)
if exist "CloudStream_TV_v4.8.0.apk" (
    echo     تثبيت CloudStream TV...
    adb install -r -d "CloudStream_TV_v4.8.0.apk" >nul 2>&1
)
if exist "SmartTube_TV_vLatest.apk" (
    echo     تثبيت SmartTube (يوتيوب بدون إعلانات وبدون نوافذ اختيار)...
    adb install -r -d "SmartTube_TV_vLatest.apk" >nul 2>&1
)

echo.
echo [7/9] 🖼️ جاري نسخ مكتبة خلفيات العتبات المقدسة ومعالم العراق إلى التلفاز...
adb shell mkdir -p /sdcard/Android/data/com.gathin.tvhome/files/wallpapers/ >nul 2>&1
adb shell mkdir -p /sdcard/Pictures/Wallpapers/ >nul 2>&1
for %%f in (wallpapers\*.jpg) do (
    echo     نسخ %%f ...
    adb push "%%f" /sdcard/Pictures/Wallpapers/ >nul 2>&1
    adb push "%%f" /sdcard/Android/data/com.gathin.tvhome/files/wallpapers/ >nul 2>&1
)

echo.
echo [8/9] ⚙️ ضبط الأذونات واعتراض زر الهوم وتعيين رافدين تي في كواجهة رئيسية...
adb shell pm grant com.gathin.tvhome android.permission.WRITE_SECURE_SETTINGS >nul 2>&1
adb shell settings put secure enabled_accessibility_services com.gathin.tvhome/.HomeKeyService >nul 2>&1
adb shell settings put secure accessibility_enabled 1 >nul 2>&1
adb shell appops set com.gathin.tvhome SYSTEM_ALERT_WINDOW allow >nul 2>&1
adb shell pm grant com.majnioui.tv.prayer android.permission.RECEIVE_BOOT_COMPLETED >nul 2>&1
adb shell cmd package set-home-activity com.gathin.tvhome/.MainActivity >nul 2>&1

echo.
echo [9/9] 🚀 فتح الشاشة الرئيسية الجديدة...
adb shell input keyevent KEYCODE_HOME

echo.
echo ======================================================================
echo    🎉 مبارك! تم تثبيت وضبط مشروع رافدين تي في بنجاح على شاشتك.
echo    يمكنك الآن التمتع بأسرع واجهة عربية نقية 100%% خالية من أي إعلانات.
echo ======================================================================
echo.
pause
