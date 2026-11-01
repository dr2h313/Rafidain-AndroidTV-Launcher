@echo off
chcp 65001 >nul
color 0A
title فحص الأمان والنسخ الاحتياطي المسبق للشاشات - Smart TV Pre-Flight v1.0

echo ======================================================================
echo       🛡️ بروتوكول الفحص المسبق والنسخ الاحتياطي التلقائي للشاشات
echo                    (Smart TV Pre-Flight Engine)
echo ======================================================================
echo.

set /p TV_IP="أدخل عنوان IP الشاشة (مثال 192.168.88.32): "
if "%TV_IP%"=="" (
    echo [!] لم يتم إدخال عنوان IP. إلغاء العملية.
    pause
    exit /b
)

echo.
echo [1/4] 🌐 جاري التحقق من الاتصال بالـ ADB (%TV_IP%)...
adb connect %TV_IP%
adb -s %TV_IP% get-state >nul 2>&1
if errorlevel 1 (
    echo [X] فشل الاتصال بالشاشة! تأكد من تشغيل ADB Mode وأن الشاشة على نفس الشبكة.
    pause
    exit /b
)
echo [+] تم الاتصال بنجاح.

set BACKUP_DIR=backup_tv_%TV_IP%
if not exist "%BACKUP_DIR%" mkdir "%BACKUP_DIR%"

echo.
echo [2/4] 📸 جاري سحب لقطة إسناد كاملة للحزم والإعدادات (Snapshot)...
adb -s %TV_IP% shell pm list packages -e > "%BACKUP_DIR%\enabled_packages.txt"
adb -s %TV_IP% shell pm list packages -d > "%BACKUP_DIR%\disabled_packages.txt"
adb -s %TV_IP% shell settings list global > "%BACKUP_DIR%\settings_global.txt"
adb -s %TV_IP% shell getprop > "%BACKUP_DIR%\device_build_props.txt"
echo [+] تم حفظ النسخة الاحتياطية في المجلد: %BACKUP_DIR%

echo.
echo [3/4] 🔍 فحص لانشر المصنع الأساسي والتبعيات الحساسة...
adb -s %TV_IP% shell cmd package resolve-activity --brief -a android.intent.action.MAIN -c android.intent.category.HOME > "%BACKUP_DIR%\current_default_launcher.txt"
type "%BACKUP_DIR%\current_default_launcher.txt"

echo.
echo [4/4] 🛡️ إنشاء ملف الإنقاذ التلقائي الخاص بهذه الشاشة...
(
    echo @echo off
    echo chcp 65001 ^>nul
    echo title سكريبت الإنقاذ الفوري للشاشة %TV_IP%
    echo echo جاري الاتصال وإنقاذ الشاشة...
    echo adb connect %TV_IP%
    echo adb -s %TV_IP% shell pm enable cn.cultraview.launcher.technical.open ^>nul 2^>^&1
    echo adb -s %TV_IP% shell pm enable com.google.android.tvlauncher ^>nul 2^>^&1
    echo adb -s %TV_IP% shell am start -a android.intent.action.MAIN -c android.intent.category.HOME
    echo echo تم إنقاذ الشاشة بنجاح!
    echo pause
) > "%BACKUP_DIR%\emergency_rescue_%TV_IP%.bat"

echo.
echo ======================================================================
echo    🎉 تم اكتمال الفحص المسبق بنجاح! الشاشة محمية الآن ويمكن تطويرها بأمان.
echo ======================================================================
echo.
pause
