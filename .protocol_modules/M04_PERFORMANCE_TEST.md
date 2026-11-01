# ⚡ الوحدة 4: الأداء المقيس، الذاكرة وتخسيس الحزم (M04_PERFORMANCE_TEST)
## Measurable Performance, Baseline Profiles, LeakCanary, 16KB Page Size & R8

---

## 1. التوافق الإلزامي مع صفحات الذاكرة 16KB (Android 15+)
* **شرط القبول الإلزامي في Google Play**:
  - فحص جميع المكتبات المضمنة ذات الكود الأصلي (C++ / NDK / SQLite / BoringSSL) للتأكد من تصريفها مع محاذاة صفحات الذاكرة بحجم 16KB (`16KB Page Size Alignment`).
  - التحقق عبر الأمر:
    `dumpelf -V <library.so> | grep LOAD`
  - التأكد من عدم وجود أي انهيارات غير متوقعة على الأجهزة والأنظمة الحديثة.

---

## 2. ملامح خط الأساس وتحسين سرعة البدء (Baseline Profiles & Macrobenchmark)
* **تسريع بدء التطبيق (App Startup Optimization)**:
  - توليد وإدراج ملف **Baseline Profile** لتقليص زمن الإقلاع الأولي بنسبة تصل إلى 40%، وتقليل وقت أول إطار تفاعلي (TTID - Time to Initial Display) إلى أقل من `1.5 ثانية`.
* **اختبارات الأداء الآلية (Macrobenchmark)**:
  - قياس زمن الانتقال بين الشاشات واستجابة التمرير في القوائم للتأكد من ثبات ميزانية الإطار (`Frame render time < 16ms`).

---

## 3. الفحص الصارم لتسريبات الذاكرة (Memory Leak Prevention)
* **التخلص الصارم من الموارد (Lifecycle Disposals)**:
  - استخدام **LeakCanary** في وضع التطوير لكشف أي تسريب لسياق النظام (`Context Leakage`) أو الـ ViewModels.
  - في Flutter: إلزامية التخلص التام من جميع وحدات التحكم (`Controllers`)، والرسوم المتحركة (`AnimationControllers`)، ومستمعي التدفقات (`StreamSubscriptions`) في تابع `dispose()`.

---

## 4. أحجام الحزم وتخسيس الأصول (R8 Full Mode & Asset Diet)
* **حظر أحجام الـ Debug المتضخمة في الإنتاج**:
  - لا يُقبل ملف APK يتجاوز 50-80MB أو AAB يتجاوز 30-50MB في النسخة النهائية المخصصة للنشر.
  - تفعيل `isMinifyEnabled = true` و `isShrinkResources = true` لتمكين تعتيم R8 وتقليم الموارد غير المستخدمة.
  - ضغط كافة الأصول والصور وتحويلها إلى صيغ حديثة خفيفة (`WebP` / `SVG`).
