# 🛡️ البروتوكول الهندسي العالمي الموحد والمصنع الآلي لأصول المتجر v12.0
## (The Supreme Universal Engineering, Security, Localization & Store Visual Engine Protocol)
### 🏛️ المعتمد لجميع مشاريع وتطبيقات: د. حسين الجابري (Dr. Hussein Al-Jaberi)
**المرجع الهندسي والتنفيذي الدائم الموحد - المعمارية المعيارية المقسمة (Core + 6 Modules)**

---

## 🧭 الهيكل المعماري لنظام v12.0 الموحد

لحماية تركيز الوكيل الذكي ومنع متلازمة التشتت في السياق الطويل (Lost-in-the-Middle)، يتألف النظام من:
1. **النواة الصلبة الدائمة ([`PROTOCORE.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/PROTOCORE.md))**: أقل من 120 سطراً تحتوي على القوانين الخمسة التي لا تقبل المساومة.
2. **الموديولات التخصصية الستة ([`.protocol_modules/`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/))**:
   - [`M01_ARCH_AND_DB.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M01_ARCH_AND_DB.md): المعمارية النظيفة، Offline-First، اختبارات الترحيل، والتعافي الذاتي من التلف، وسقف الـ Cache.
   - [`M02_SECURITY_PRIVACY.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M02_SECURITY_PRIVACY.md): KeyStore AES-256 GCM، قواعد dataExtractionRules، حظر أسرار العميل، وحذف وتصدير البيانات.
   - [`M03_UI_UX_A11Y.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M03_UI_UX_A11Y.md): Material 3 Expressive، العرض الكامل Edge-to-Edge، التدرج الرسومي لضمان 60fps، و TalkBack.
   - [`M04_PERFORMANCE_TEST.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M04_PERFORMANCE_TEST.md): ملامح خط الأساس Baseline Profiles، صفحات 16KB، ومنع تسريب الذاكرة، وأحجام الـ Release.
   - [`M05_STORE_VISUAL_PIPELINE.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M05_STORE_VISUAL_PIPELINE.md): **المصنع الآلي للافتة وسكرينات المتجر** عبر التصوير الحي + محرك HTML/CSS و Playwright.
   - [`M06_COMPLIANCE_AND_OBSERVABILITY.md`](file:///c:/Users/Ali%20Al-Jaberi/antigravity/calm-babbage/.protocol_modules/M06_COMPLIANCE_AND_OBSERVABILITY.md): الاختبار المغلق 14 يوماً، In-App Updates/Reviews، الصلاحيات، و Crashlytics.

---

## 🎨 تفاصيل المصنع الآلي لأصول المتجر البصرية (Store Visual Engine):

### 1. علة وسر الحل الهندسي الجذري:
* **حظر التوليد البرمجي البدائي عبر PIL**: منع محاولة رسم أشكال هندسية بدائية بالبايثون؛ لأنها تنتج لافتات خالية وغير مقبولة.
* **خط الإنتاج المعتمد**:
  1. التقاط شاشات حقيقية من التطبيق الفعلي باللغتين العربية والإنجليزية وحفظها في `store_assets/raw_captures/`.
  2. تصميم القالب الفاخر عبر HTML/CSS مع خطوط عربية حديثة (Cairo/Tajawal)، خامات الزجاج والظلال الواقعية ومجسمات الهواتف ثلاثية الأبعاد.
  3. رندر القوالب والتقاطها عبر متصفح **Playwright** بدقة `1024x500` للافتة و `1080x2400` للقطات الشاشة.
  4. فحص بصري ذاتي إلزامي للصور قبل تسليم المشروع.

### 2. مواصفات اللافتة الترويجية الفائقة (`feature_graphic_1024x500.png`):
- خلفية متدرجة سينمائية عميقة ذات توهج إضاءة ناعم.
- شعار التطبيق المعتمد داخل إطار زجاجي ناعم الحواف وظلال واقعية.
- التيبوغرافيا الثنائية الاحترافية (اسم التطبيق بالعربية والإنجليزية + وسام الإصدار Pro).
- مجسم هاتف رائد 3D مائل يعرض شاشة حقيقية حية من داخل التطبيق.
- 4 كبسولات عائمة لأبرز الميزات وبطاقة مؤشر رقمي واقعي (KPI Card).

### 3. مواصفات لقطات الشاشة الترويجية (`store_assets/screenshots/`):
- ما لا يقل عن **5 لقطات عربية و 5 لقطات إنجليزية** بدقة `1080x2400 px`.
- التوزيع الرأسي القياسي:
  * 22% شريط علوي تسويقي (عنوان بارز + وصف فرعي تحويلي).
  * 68% إطار هاتف 3D مع الشاشة الحقيقية للتطبيق وظلال هبوط ناعمة.
  * 10% شريط سفلي لكبسولات الميزات والوسوم السريعة.

---

## 🎯 مصفوفة القبول والاعتماد النهائي (Definition of Done - DoD v12.0):

| المعيار الهندسي | الحد الصارم المطلوب | الأداة وطريقة الفحص |
| :--- | :--- | :--- |
| **1. اللافتة الترويجية (Feature Graphic)** | لافتة مركبة غنية (1024x500) تضم الشعار، نصوص AR/EN، مجسم هاتف 3D بشاشة حقيقية، كبسولات وبطاقة مؤشر | فحص بصري لملف `feature_graphic_1024x500.png` |
| **2. سكرينات المتجر (Screenshots)** | 5 لقطات عربية + 5 لقطات إنجليزية (`1080x2400`) حقيقية ومؤطرة في مجسمات 3D | فحص مجلدات `screenshots/ar/` و `en/` |
| **3. التسمية الثلاثية وحجم الحزمة** | بناء حزمة `release` مع R8 وتصدير APK (<80MB) و AAB (<50MB) إلى `/releases_output/` | فحص محتويات المجلد ومطابقة المسميات والـ Git-Safe |
| **4. اسم شاشة الهاتف (Launcher Name)** | اسم نقي فقط بدون رقم إصدار نهائياً (يتكيف عربي في values-ar وإنجليزي في values) | فحص `strings.xml` في اللغتين |
| **5. التوطين الثنائي وإمكانية الوصول** | 0 نصوص صلبة، تبديل فوري Per-App (<50ms)، مساحات لمس >=48dp، وتكبير الخط 200% | فحص الموارد واختبار TalkBack ومحاذاة RTL |
| **6. الأمان وسلامة البيانات والـ 16KB** | KeyStore AES-256، ملف dataExtractionRules، منع Destructive Migration، وتوافق 16KB | نجاح الاختبارات الآلية (`Exit Code 0`) وفحص الـ Manifest |
| **7. أمان الشاشات الذكية (Smart TV Safe Rule)** | حظر تعطيل لانشر المصنع إطلاقاً (`pm disable`) لمنع تعليق الإقلاع، والاكتفاء بتوجيه Home Intent، وسحب Snapshot مسبق | فحص توافق `smart_tv_preflight.bat` وعدم المساس بـ Vendor Boot |
