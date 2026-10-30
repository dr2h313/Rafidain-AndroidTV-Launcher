@echo off
chcp 65001 >nul
color 0C
title Rafidain TV - استعادة الواجهة الأصلية للتلفاز

echo ======================================================================
echo           استعادة الواجهة الأصلية الافتراضية للتلفاز
echo ======================================================================
echo.

set DEFAULT_IP=192.168.88.37:5555
set /p TV_IP="أدخل عنوان IP التلفاز (اضغط Enter للافتراضي %DEFAULT_IP%): "
if "%TV_IP%"=="" set TV_IP=%DEFAULT_IP%

echo.
echo [1/3] 🌐 جاري الاتصال بالتلفاز عبر الشبكة (%TV_IP%)...
adb connect %TV_IP%
timeout /t 2 >nul

echo.
echo [2/3] 🔄 إعادة تعيين مشغل الشاشة الافتراضي للمصنع (Google TV Launcher)...
adb shell cmd package set-home-activity com.google.android.apps.tv.launcherx/.home.HomeActivity >nul 2>&1
adb shell cmd package set-home-activity com.google.android.tvlauncher/.MainActivity >nul 2>&1

echo.
echo [3/3] 🏠 فتح الشاشة الرئيسية الأصلية...
adb shell input keyevent KEYCODE_HOME

echo.
echo ======================================================================
echo    تمت استعادة الواجهة الأصلية للتلفاز بنجاح.
echo ======================================================================
echo.
pause
