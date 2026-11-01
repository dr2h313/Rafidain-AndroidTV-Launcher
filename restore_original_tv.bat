@echo off
chcp 65001 >nul
color 0B
title استرجاع التلفاز للوضع المصنعي الأصلي - 1-Click Rollback Script

echo ======================================================================
echo    🛡️ أداة استرجاع تلفاز ميدياستار للوضع المصنعي الأصلي بنقرة واحدة
echo               (Restore Factory Mediastar TV v1.0)
echo ======================================================================
echo.
echo هذه الأداة تعيد جميع تطبيقات وخدمات المصنع الأصلية المعطلة،
echo وتعيد الواجهة الافتراضية الرسمية (Cultraview Launcher)،
echo وتلغي جميع التعديلات بأمان تام 100%%.
echo.

set DEFAULT_IP=192.168.88.31:5555
set /p TV_IP="أدخل عنوان IP التلفاز (اضغط Enter للافتراضي %DEFAULT_IP%): "
if "%TV_IP%"=="" set TV_IP=%DEFAULT_IP%

echo.
echo [1/5] 🌐 جاري الاتصال بالتلفاز (%TV_IP%)...
adb connect %TV_IP%

echo.
echo [2/5] 🔄 جاري إعادة تفعيل كافة خدمات وتطبيقات المصنع المعطلة...
adb -s %TV_IP% shell pm enable cn.cultraview.launcher.technical.open
adb -s %TV_IP% shell pm enable com.android.vending
adb -s %TV_IP% shell pm enable com.google.android.googlequicksearchbox
adb -s %TV_IP% shell pm enable com.zeasn.services.general
adb -s %TV_IP% shell pm enable com.zeasn.deviceportal.asdprovider.cetus.pub
adb -s %TV_IP% shell pm enable cn.zeasn.cultraview.msd358.vstoresubclient.cetus1a.mtk6681
adb -s %TV_IP% shell pm enable com.ctv.autotest.helper
adb -s %TV_IP% shell pm enable com.ctv.hotelsetting
adb -s %TV_IP% shell pm enable com.ctv.hotelsettingoverlay
adb -s %TV_IP% shell pm enable com.cultraview.osupdate
adb -s %TV_IP% shell pm enable com.android.browser
adb -s %TV_IP% shell pm enable com.android.soundrecorder
adb -s %TV_IP% shell pm enable com.android.ctvguide
adb -s %TV_IP% shell pm enable com.android.ctvguideoverlay
adb -s %TV_IP% shell pm enable com.cultraview.screensaver
adb -s %TV_IP% shell pm enable com.ecloud.eshare.server
adb -s %TV_IP% shell pm enable com.ecloud.eairplay
adb -s %TV_IP% shell pm enable com.ecloud.emedia

echo.
echo [3/5] ⚙️ إعادة تعيين سرعة الرسوم والحركة والـ DNS للوضع الافتراضي...
adb -s %TV_IP% shell settings put global window_animation_scale 1.0
adb -s %TV_IP% shell settings put global transition_animation_scale 1.0
adb -s %TV_IP% shell settings put global animator_duration_scale 1.0
adb -s %TV_IP% shell settings put global private_dns_mode off
adb -s %TV_IP% shell settings delete global private_dns_specifier
adb -s %TV_IP% shell settings put global force_hw_ui 0

echo.
echo [4/5] 🏠 تعيين واجهة المصنع الرسمية كواجهة رئيسية...
adb -s %TV_IP% shell cmd package set-home-activity cn.cultraview.launcher.technical.open/com.cultraview.launcher.technical.open.ui.MainActivity

echo.
echo هل ترغب في إزالة التطبيقات المخصصة (CleanYourTV و SmartTube)؟
set /p REMOVE_CUSTOM="اكتب y للموافقة أو اضغط Enter للإبقاء عليها: "
if /i "%REMOVE_CUSTOM%"=="y" (
    echo جاري إزالة التطبيقات المخصصة...
    adb -s %TV_IP% shell pm uninstall com.gathin.tvhome >nul 2>&1
    adb -s %TV_IP% shell pm uninstall org.smarttube.stable >nul 2>&1
)

echo.
echo [5/5] 🚀 إرسال أمر العودة للواجهة الرئيسية...
adb -s %TV_IP% shell input keyevent KEYCODE_HOME

echo.
echo ======================================================================
echo      🎉 تم استرجاع التلفاز للوضع المصنعي الأصلي بالكامل بنجاح!
echo ======================================================================
echo.
pause
