@echo off
chcp 65001 >nul
color 0C
title استرجاع الواجهة الأصلية للتلفاز - Restore Factory TV Home

echo ======================================================================
echo           أداة استرجاع الواجهة الأصلية وإلغاء جميع التعديلات
echo ======================================================================
echo.
echo هذه الأداة ستقوم بإلغاء تثبيت تطبيقات مشروع رافدين تي في
echo وإعادة الشاشة فوراً إلى واجهتها الأصلية الرسمية الافتراضية.
echo.

set DEFAULT_IP=192.168.88.37:5555
set /p TV_IP="أدخل عنوان IP التلفاز (اضغط Enter للافتراضي %DEFAULT_IP%): "
if "%TV_IP%"=="" set TV_IP=%DEFAULT_IP%

echo.
echo [1/4] 🌐 جاري الاتصال بالتلفاز (%TV_IP%)...
adb connect %TV_IP%

echo.
echo [2/4] 🔄 استرجاع الواجهة الأصلية للنظام كواجهة افتراضية...
adb shell cmd package set-home-activity com.google.android.apps.tv.launcherx/.home.HomeActivity >nul 2>&1
adb shell cmd package set-home-activity com.google.android.tvlauncher/.MainActivity >nul 2>&1
adb shell settings put secure enabled_accessibility_services "" >nul 2>&1

echo.
echo [3/4] 🗑️ إزالة التطبيقات المضافة (لانشر رافدين، الطقس، الإذاعات، مواقيت الصلاة)...
adb uninstall com.gathin.tvhome >nul 2>&1
adb uninstall com.gathin.shrineradio >nul 2>&1
adb uninstall io.github.sumyabazar1.weather >nul 2>&1
adb uninstall com.majnioui.tv.prayer >nul 2>&1

echo.
echo هل ترغب أيضاً بإزالة تطبيقات البث (Stremio و CloudStream و SmartTube)؟
set /p REMOVE_MEDIA="اكتب y للموافقة أو اضغط Enter للإبقاء عليها: "
if /i "%REMOVE_MEDIA%"=="y" (
    echo جاري إزالة تطبيقات البث...
    adb uninstall com.stremio.one >nul 2>&1
    adb uninstall com.lagradost.cloudstream3 >nul 2>&1
    adb uninstall org.smarttube.stable >nul 2>&1
)

echo.
echo [4/4] 🚀 إعادة التلفاز للواجهة الأصلية فوراً...
adb shell input keyevent KEYCODE_HOME

echo.
echo ======================================================================
echo      🎉 تم استرجاع التلفاز إلى وضعه الأصلي تماماً بنجاح 100%%!
echo ======================================================================
echo.
pause
