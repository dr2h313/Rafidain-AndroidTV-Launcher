# 🛡️ البروتوكول الهندسي الشامل والتحصين المؤسسي للمشاريع v11.0
## (The Supreme Universal Engineering, Security, Localization & Store Architecture Protocol - Modular Edition)
### 🏛️ المعتمد لجميع مشاريع وتطبيقات: د. حسين الجابري (Dr. Hussein Al-Jaberi)
**المرجع الهندسي والتنفيذي الدائم الموحد - المعمارية المعيارية المقسمة (Core + Modules)**

---

## 🧭 هيكل البروتوكول المعماري (v11.0 Modular Architecture)

لتفادي تشتت انتباه الوكلاء البرمجية (Lost-in-the-Middle) وضمان التنفيذ الصارم بنسبة 100% دون إهمال أي قاعدة، تم تقسيم البروتوكول إلى:
1. **دستور النواة الصلبة (`PROTOCORE.md`)**: ملف خفيف ومكثف يحدد القواعد الإلزامية الخمس التي لا تقبل المساومة.
2. **الموديولات التخصصية المشروطة (`.protocol_modules/`)**: ملفات تفصيلية تُستدعى وتُقرأ عند العمل على مرحلتها المحددة فقط:
   - [`M01_ARCH_AND_DB.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M01_ARCH_AND_DB.md): المعمارية النظيفة، Offline-First، اختبارات ترحيل البيانات، وحوكمة الـ Cache.
   - [`M02_SECURITY_PRIVACY.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M02_SECURITY_PRIVACY.md): KeyStore AES-256، قواعد dataExtractionRules، حظر أسرار العميل، وحقوق المستخدم.
   - [`M03_UI_UX_A11Y.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M03_UI_UX_A11Y.md): Material 3 Expressive، Edge-to-Edge، التدرج الرسومي وإمكانية الوصول A11y.
   - [`M04_NETWORK_AI.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M04_NETWORK_AI.md): الشبكات المؤمنة، SSL Pinning المرن، الوسيط الخلفي، وحماية الذكاء الاصطناعي.
   - [`M05_PERFORMANCE_TEST.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M05_PERFORMANCE_TEST.md): ملامح خط الأساس Baseline Profiles، صفحات 16KB، ومنع تسريب الذاكرة.
   - [`M06_STORE_ASSETS_AND_ASO.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M06_STORE_ASSETS_AND_ASO.md): المحرك البصري لحزمة المتجر (اللافتة المركبة 1024x500، سكرينات 3D Mockup 1080x2400، وأصول ASO).
   - [`M07_RELEASE_AND_COMPLIANCE.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M07_RELEASE_AND_COMPLIANCE.md): أتمتة Fastlane، الاختبار المغلق 14 يوماً، In-App APIs، ومراقبة Crashlytics.

---

## 🛑 ملخص القواعد الإلزامية الخمس الحاكمة:

### 1. حوكمة الوكيل والتنقيح المنهجي:
- منع التخمين؛ تتبع تدفق البيانات للخلف إلى الجذر الأول (RCA).
- منع إنهاء أي مرحلة دون إثبات النجاح الفعلي الرقمي الصريح (`Exit Code 0`).

### 2. التسمية الثلاثية القياسية والتصدير الآمن (علي الجابري):
- حظر المسميات الافتراضية العامة مثل `app-release.apk`.
- التصدير التلقائي إلى مجلد المخرجات الآمن `/releases_output/` (المضاف لـ `.gitignore` لمنع تلويث الـ Git بالملفات الثنائية الضخمة):
  1. `[AppName_En]_v[VersionName]_[buildType].apk`
  2. `[AppName_En]_v[VersionName]_[buildType].aab`
- أرشفة ملف التعتيم في `/mappings_archive/v[VersionName]/[AppName_En]_v[VersionName]_mapping.txt`.
- اسم شاشة الهاتف (Launcher Name) نقي بدون أي رقم إصدار (يتكيف تلقائياً عربي/إنجليزي).

### 3. التوطين الثنائي الإلزامي الكامل وإمكانية الوصول:
- دعم 100% للعربية والإنجليزية؛ إذا وُجد التطبيق بالعربية فقط، يُلزم بتوليد وإضافة النسخة الإنجليزية فوراً.
- التبديل الفوري للغة عبر `Per-App Language API` (استجابة < 50ms دون إعادة تشغيل التطبيق).
- هندسة الاتجاه الأصيلة (RTL/LTR)، ومساحات اللمس `>= 48dp`، وتكبير الخط 200%، ودعم TalkBack.

### 4. المحرك البصري الإلزامي لحزمة المتجر (Store Assets Engine):
- **حظر اللافتات الفارغة**: إلزامية إنتاج لافتة ترويجية مركبة فائقة الغنى (`1024x500 px`) تضم الشعار الزجاجي، التيبوغرافيا الثنائية، مجسم الهاتف ثلاثي الأبعاد المائل الحقيقي، كبسولات الميزات وبطاقة إحصائية حية.
- **إلزامية سكرينات المتجر**: مجموعتان كاملتان (عربية وإنجليزية) بدقة `1080x2400 px` بمعادلة 3D Mockup (شريط علوي 22% + مجسم الهاتف والشاشة الحقيقية 68% + وسوم الميزات 10%).
- أيقونة عالية الدقة `512x512 px` معتمة، وملف البيانات الوصفية والـ ASO باللغتين (`STORE_METADATA.md`).

### 5. الأمان وسلامة البيانات والتوافق مع Android 15/16:
- لا توجد أسرار خاصة في كود العميل (الاعتماد على وسيط خلفي مؤمن).
- حظر النسخ غير المقيد عبر `android:dataExtractionRules`.
- منع مسح البيانات عند الترقية (`No Destructive Migration`) وفرض اختبارات الترحيل ومعالج التلف التلقائي.
- دعم صفحات الذاكرة بحجم 16KB، تفعيل العرض الكامل (Edge-to-Edge)، والتدرج الرسومي (Graphic Performance Tiering) لحماية معدل 60fps على الأجهزة المتوسطة والضعيفة.

---

## 🎯 مصفوفة القبول والاعتماد النهائي (Definition of Done - DoD):
| المعيار الهندسي | الحد الصارم المطلوب | الأداة وطريقة الفحص |
| :--- | :--- | :--- |
| **تحليل الكود والاختبارات** | 0 أخطاء، 0 تحذيرات حرجة، واجتياز 100% من الاختبارات | `flutter test` / `./gradlew check` (Exit Code 0) |
| **حزمة المتجر البصرية** | لافتة مركبة غنية (1024x500) + 4-8 سكرينات 3D لكل لغة (1080x2400) + أيقونة 512 | فحص مجلد `/store_assets/` وملف `STORE_METADATA.md` |
| **التوافق مع النظام** | Android 15/16 (API 35/36)، العرض الكامل، ودعم صفحات 16KB | فحص `AndroidManifest` ومحلل الـ NDK |
| **الأداء والإطارات** | إطارات ثابتة 60/120fps (مع تحول Blur لأسطح شبه شفافة في الأجهزة الضعيفة) | Flutter DevTools / Profiler |
| **تسريب الذاكرة** | 0 تسريبات للذاكرة مع إغلاق كافة الـ Controllers | فحص دورة الحياة / LeakCanary |
| **الحزم والتسمية الثلاثية** | تصدير APK و AAB بالاسم القياسي في `/releases_output/` ومؤرشفة في `.gitignore` | فحص محتويات المجلد ومطابقة المسميات |
| **التوطين وإمكانية الوصول** | 100% AR/EN، تبديل فوري Per-App، مساحات اللمس `>= 48dp`، وتكبير الخط 200% | فحص الموارد واختبار TalkBack ومحاذاة RTL |
