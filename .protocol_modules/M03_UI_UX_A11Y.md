# 💎 موديول M03: الواجهات الملكية، التكيف الرسومي وإمكانية الوصول (UI/UX & A11y)
## Material 3 Expressive, Edge-to-Edge, Graphic Tiering & Accessibility

---

## 1. فلسفة التصميم: Material 3 Expressive مع انسيابية الحركة
* **احترام البيئة الأصلية (Native Platform Respect)**:
  - الاعتماد على **Material 3 Expressive** كأساس لتطبيقات الأندرويد للامتثال لقانون جاكوب وتوقعات مستخدمي النظام.
  - إدخال لمسات الانسيابية الفيزيائية (Spring Physics) والانتقالات السلسة (Predictive Back & Shared Axis Transitions).
* **العرض الكامل الإلزامي من الحافة للحافة (Mandatory Edge-to-Edge)**:
  - تفعيل `WindowCompat.setDecorFitsSystemWindows(window, false)` أو `enableEdgeToEdge()` في Android 15/16.
  - معالجة الحواف (`WindowInsets` / `SafeArea`) بدقة حتى لا تتداخل الأزرار مع شريط التنقل السفلي أو شريط الحالة.
  - تفعيل إيماءة الرجوع التنبؤية: `android:enableOnBackInvokedCallback="true"`.

---

## 2. مصفوفة التدرج الرسومي (Graphic Performance Tiering)
* **حل تعارض الزجاج والـ Shaders مع الأجهزة المتوسطة والضعيفة**:
  - حساب قدرة الجهاز (Device Performance Level / RAM / Refresh Rate).
  - **الأجهزة الرائدة (High-End)**: تفعيل خامات الزجاج الحية (Realtime BackdropFilter / RenderEffect Blur) والظلال المتعددة.
  - **الأجهزة الاقتصادية والمتوسطة (Mid/Low-End)**: التحول التلقائي إلى أسطح شبه شفافة مصمتة (Static Tinted Glass Surfacing مع Alpha Blending خفيف) للحفاظ على ثبات معدل الإطارات عند `60fps` ومنع هبوطها.

---

## 3. إمكانية الوصول الشاملة (Accessibility - A11y)
* **قارئات الشاشة (TalkBack)**:
  - توفير نصوص دلالية واضحة (`contentDescription` / `Semantics(label: ...)`) لكل زر أو أيقونة أو بطاقة تفاعلية.
  - حجب العناصر التزيينية البحتة عن قارئ الشاشة (`importantForAccessibility="no"` / `excludeFromSemantics: true`).
* **تكبير النصوص الديناميكي (Dynamic Font Scaling 200%)**:
  - اختبار واجهات التطبيق تحت تكبير خط النظام بنسبة `200%` والتأكد التام من عدم حدوث تجاوز للنصوص أو أخطاء `RenderFlex overflow`.
* **مساحات اللمس القياسية (Touch Targets)**:
  - جميع الأزرار وعناصر التحكم القابلة للنقر يجب ألا تقل أبعادها عن `48x48 dp` (مساحة لمس مريحة للإبهام).
* **نسبة التباين اللوني (Contrast Ratio)**:
  - نسبة تباين النصوص العادية مع الخلفية لا تقل عن `4.5:1`، وللنصوص الكبيرة عن `3:1` في الوضعين الليلي والنهاري.
