# 🔒 موديول M02: الأمان، الخصوصية وسلاسل الإمداد (Security & Privacy)
## KeyStore Encryption, Data Extraction Rules, Zero Client Secrets & Privacy Rights

---

## 1. التشفير المحلي وحماية المفاتيح (Android KeyStore & AES-256 GCM)
* **تشفير البيانات الحساسة**: استخدام `MasterKeys` و `EncryptedSharedPreferences` المشفرة عبر **Android KeyStore** مع معيار `AES-256 GCM`.
* **حظر الأسرار الثابتة (Zero Hardcoded Private Secrets)**:
  - لا توجد أسرار خاصة مدفوعة في كود العميل إطلاقاً؛ لأن أي مفتاح داخل ملف APK يمكن فكه بالهندسة العكسية.
  - المفاتيح العامة (مثل Firebase) تُقيّد في منصة Google Cloud ببصمة التوقيع `SHA-256` واسم الحزمة (Package Name).
  - المفاتيح الحساسة (LLM APIs، بوابات الدفع) يجب أن تُستدعى حصراً عبر وسيط خلفي مؤمن (Backend Proxy / Cloudflare Worker).

---

## 2. قواعد استخراج البيانات وحماية النسخ الاحتياطي (Android 12+)
* **حظر النسخ الاحتياطي غير المقيد**:
  - ضبط `android:allowBackup="false"` في `AndroidManifest.xml`.
  - تعريف قواعد استخراج البيانات الصريحة عبر `android:dataExtractionRules="@xml/data_extraction_rules"` و `android:fullBackupContent="@xml/backup_rules"`.
  - استثناء مجلدات قواعد البيانات والملفات المشفرة صراحةً من النسخ السحابي ونقل الجهاز لجهاز آخر (Device-to-Device Transfer).

---

## 3. حوكمة الصلاحيات الحساسة في Android 14+ و 15 و 16
* **شاشات التبرير المسبق (Pre-Permission Rationale UI)**:
  - عند طلب إذن الإشعارات (`POST_NOTIFICATIONS`) أو الكاميرا، يجب عرض شاشة أو نافذة تمهيدية داخلية تشرح للمستخدم الفائدة الوظيفية قبل ظهور نافذة النظام، لتفادي الرفض الدائم.
* **خدمات الواجهة الأمامية (Foreground Services)**:
  - تحديد النوع الصريح (`android:foregroundServiceType`) المطلوب في الـ Manifest وربطه بالإشعار الدائم المتوافق مع سياسات Google Play.
* **استبدال صلاحيات التخزين بـ Photo Picker**:
  - حظر طلب `READ_EXTERNAL_STORAGE` أو `READ_MEDIA_IMAGES` كلما أمكن، والاعتماد على منتقي الصور الأصلي للنظام (`Photo Picker`) الذي لا يتطلب أي صلاحيات أمنية.

---

## 4. حوكمة الخصوصية وحقوق المستخدم (Privacy & User Rights)
* **آلية حذف الحساب والبيانات (Account & Data Deletion)**:
  - توفير خيار صريح ومباشر داخل الإعدادات لحذف الحساب ومسح كافة البيانات محلياً وسحابياً دون أي تعقيد، امتثالاً لسياسات Google Play الإلزامية.
* **تصدير واستعادة البيانات (Export & Backup)**:
  - توفير زر لتصدير نسخة احتياطية من بيانات المستخدم بصيغة آمنة ومفتوحة (JSON/CSV) واستعادتها بسهولة.
