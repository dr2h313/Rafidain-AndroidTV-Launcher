package com.gathin.tvhome;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Calendar;

/**
 * محرك نور العترة:
 * أحاديث الحكمة وأدعية الأيام المأثورة عن النبي وأهل بيته الأطهار (عليهم السلام)
 */
public final class AhlulbaytWisdomHelper {

    public static class DailyWisdom {
        public final String dayName;
        public final String hadithText;
        public final String hadithSource;
        public final String duaTitle;
        public final String duaContent;

        public DailyWisdom(String dayName, String hadithText, String hadithSource, String duaTitle, String duaContent) {
            this.dayName = dayName;
            this.hadithText = hadithText;
            this.hadithSource = hadithSource;
            this.duaTitle = duaTitle;
            this.duaContent = duaContent;
        }
    }

    private static final DailyWisdom[] WEEKLY_WISDOM = {
        // 0: الأحد (Sunday - Calendar.SUNDAY = 1)
        new DailyWisdom(
            "الأحد",
            "قال رسول الله (ص): «حُسْنُ الْخُلُقِ يُثْبِتُ الْمَوَدَّةَ وَيُهْدِمُ الْعَدَاوَةَ»",
            "بحار الأنوار",
            "دعاء يوم الأحد",
            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\nبِسْمِ اللَّهِ الَّذِي لا أَرْجُو إِلَّا فَضْلَهُ، وَلا أَخْشَى إِلَّا عَدْلَهُ، وَلا أَعْتَمِدُ إِلَّا قَوْلَهُ، وَلا أُمْسِكُ إِلَّا بِحَبْلِهِ. بِكَ أَسْتَجِيرُ يَا ذَا الْعَفْوِ وَالرِّضْوَانِ مِنَ الظُّلْمِ وَالْعُدْوَانِ، وَمِنْ غِيَرِ الزَّمَانِ، وَتَوَاتُرِ الْأَحْزَانِ، وَطَوَارِقِ الْحَدَثَانِ... اللَّهُمَّ اجْعَلْ أَوَّلَ يَوْمِي هَذَا صَلاحاً، وَأَوْسَطَهُ فَلاحاً، وَآخِرَهُ نَجَاحاً."
        ),
        // 1: الاثنين (Monday - Calendar.MONDAY = 2)
        new DailyWisdom(
            "الاثنين",
            "قال أمير المؤمنين علي (ع): «الْعَفْوُ زَكَاةُ الْقُدْرَةِ، وَالتَّوَاضُعُ زَكَاةُ الشَّرَفِ»",
            "نهج البلاغة",
            "دعاء يوم الاثنين",
            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\nالْحَمْدُ لِلَّهِ الَّذِي لَمْ يُشْهِدْ أَحَداً حِينَ فَطَرَ السَّمَاوَاتِ وَالْأَرْضَ، وَلا اتَّخَذَ مُعِيناً حِينَ بَرَأَ النَّسَمَاتِ... اللَّهُمَّ أَنْتَ رَبِّي لا إِلَهَ إِلَّا أَنْتَ، وَأَنَا عَبْدُكَ، وَعَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ. اللَّهُمَّ أَعْطِنِي فِي هَذَا الْيَوْمِ أَمْناً مِنْ عَذَابِكَ، وَرِضْوَاناً مِنْ جَزَائِكَ، وَأَلْهِمْنِي شُكْرَ نَعْمَائِكَ."
        ),
        // 2: الثلاثاء (Tuesday - Calendar.TUESDAY = 3)
        new DailyWisdom(
            "الثلاثاء",
            "قال الإمام جعفر الصادق (ع): «إِنَّ أَحَبَّ الْأَعْمَالِ إِلَى اللَّهِ سُرُورٌ يُدْخِلُهُ الْمُؤْمِنُ عَلَى الْمُؤْمِنِ»",
            "الكافي الشريف",
            "دعاء يوم الثلاثاء",
            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\nالْحَمْدُ لِلَّهِ وَالْحَمْدُ حَقُّهُ كَمَا يَسْتَحِقُّهُ حَمْداً كَثِيراً، وَأَعُوذُ بِهِ مِنْ شَرِّ نَفْسِي، إِنَّ النَّفْسَ لَأَمَّارَةٌ بِالسُّوءِ إِلَّا مَا رَحِمَ رَبِّي... اللَّهُمَّ اجْعَلِ الْحَيَاةَ زِيَادَةً لِي فِي كُلِّ خَيْرٍ، وَالْوَفَاةَ رَاحَةً لِي مِنْ كُلِّ شَرٍّ."
        ),
        // 3: الأربعاء (Wednesday - Calendar.WEDNESDAY = 4)
        new DailyWisdom(
            "الأربعاء",
            "قال الإمام الحسين (ع): «إِنَّ حَوَائِجَ النَّاسِ إِلَيْكُمْ مِنْ نِعَمِ اللَّهِ عَلَيْكُمْ، فَلا تَمَلُّوا النِّعَمَ فَتَتَحَوَّلَ نِقَماً»",
            "تحف العقول",
            "دعاء يوم الأربعاء",
            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\nالْحَمْدُ لِلَّهِ الَّذِي جَعَلَ اللَّيْلَ لِبَاساً، وَالنَّوْمَ سُبَاتاً، وَجَعَلَ النَّهَارَ نُشُوراً... اللَّهُمَّ اقْضِ لِي فِي الْأَرْبِعَاءِ أَرْبَعاً: اجْعَلْ قُوَّتِي فِي طَاعَتِكَ، وَنَشَاطِي فِي عِبَادَتِكَ، وَرَغْبَتِي فِي ثَوَابِكَ، وَزُهْدِي فِيمَا يُوجِبُ لِي أَلِيمَ عِقَابِكَ."
        ),
        // 4: الخميس (Thursday - Calendar.THURSDAY = 5)
        new DailyWisdom(
            "الخميس",
            "قال الإمام علي الرضا (ع): «أَحْسِنِ الظَّنَّ بِاللَّهِ، فَإِنَّ اللَّهَ عَزَّ وَجَلَّ يَقُولُ: أَنَا عِنْدَ ظَنِّ عَبْدِي بِي»",
            "عيون أخبار الرضا",
            "دعاء يوم الخميس",
            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\nالْحَمْدُ لِلَّهِ الَّذِي أَذْهَبَ اللَّيْلَ مُظْلِماً بِقُدْرَتِهِ، وَجَاءَ بِالنَّهَارِ مُبْصِراً بِرَحْمَتِهِ، وَكَسَانِي ضِيَاءَهُ وَأَنَا فِي نِعْمَتِهِ... اللَّهُمَّ فَمَا وَهَبْتَ لِي مِنْ نِعْمَةٍ فَلا تَسْلُبْنِيهَا، وَارْزُقْنِي فِي يَوْمِ الْخَمِيسِ ثَبَاتاً فِي دِينِكَ، وَرَجَاءً فِي رَحْمَتِكَ."
        ),
        // 5: الجمعة (Friday - Calendar.FRIDAY = 6)
        new DailyWisdom(
            "الجمعة",
            "قال رسول الله (ص): «إِنَّ يَوْمَ الْجُمُعَةِ سَيِّدُ الْأَيَّامِ، فَأَكْثِرُوا فِيهِ مِنَ الصَّلاةِ عَلَيَّ وَعَلَى أَهْلِ بَيْتِي»",
            "من لا يحضره الفقيه",
            "دعاء وزيارة يوم الجمعة",
            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\nالْحَمْدُ لِلَّهِ الأوَّلِ قَبْلَ الإِنْشَاءِ وَالإِحْيَاءِ، وَالآخِرِ بَعْدَ فَنَاءِ الأَشْيَاءِ... اللَّهُمَّ اجْعَلْنِي مِنْ أَهْلِ الْجُمُعَةِ الَّذِينَ أَوْجَبْتَ لَهُمْ رَحْمَتَكَ وَغُفْرَانَكَ.\n\nزيارة الإمام المهدي (عج) يوم الجمعة:\n«السَّلامُ عَلَيْكَ يَا حُجَّةَ اللَّهِ فِي أَرْضِهِ، السَّلامُ عَلَيْكَ يَا عَيْنَ اللَّهِ فِي خَلْقِهِ، السَّلامُ عَلَيْكَ يَا نُورَ اللَّهِ الَّذِي يَهْتَدِي بِهِ الْمُهْتَدُونَ...»"
        ),
        // 6: السبت (Saturday - Calendar.SATURDAY = 7)
        new DailyWisdom(
            "السبت",
            "قال الإمام محمد الباقر (ع): «الْكَمَالُ كُلُّ الْكَمَالِ: التَّفَقُّهُ فِي الدِّينِ، وَالصَّبْرُ عَلَى النَّائِبَةِ، وَتَقْدِيرُ الْمَعِيشَةِ»",
            "أصول الكافي",
            "دعاء يوم السبت",
            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\nبِسْمِ اللَّهِ كَلِمَةِ الْمُعْتَصِمِينَ، وَمَقَالَةِ الْمُتَحَرِّزِينَ، وَأَعُوذُ بِاللَّهِ تَعَالَى مِنْ جَوْرِ الْجَائِرِينَ... اللَّهُمَّ إِنِّي أَسْأَلُكَ فِي هَذَا الْيَوْمِ صَلاحَ دِينِي وَدُنْيَايَ، وَأَنْ تُوَفِّقَنِي لِمَا تُحِبُّ وَتَرْضَى."
        )
    };

    public static DailyWisdom getTodayWisdom() {
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 = Sunday, 2 = Monday ... 7 = Saturday
        int index = (dayOfWeek - 1) % 7;
        if (index < 0 || index >= WEEKLY_WISDOM.length) index = 0;
        return WEEKLY_WISDOM[index];
    }

    /**
     * يعرض نافذة تلفزيونية فاخرة لقراءة دعاء اليوم كاملاً مع الحديث الشريف
     */
    public static void showWisdomDialog(Context context) {
        DailyWisdom wisdom = getTodayWisdom();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 30, 40, 30);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(0xF0111822);
        bg.setCornerRadius(24);
        layout.setBackground(bg);

        // Header Title
        TextView title = new TextView(context);
        title.setText("📖 نور العترة — " + wisdom.duaTitle);
        title.setTextSize(22);
        title.setTextColor(0xFF38BDF8); // Sky blue
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, 16);
        layout.addView(title);

        // Hadith Box
        LinearLayout hadithBox = new LinearLayout(context);
        hadithBox.setOrientation(LinearLayout.VERTICAL);
        hadithBox.setPadding(24, 16, 24, 16);
        GradientDrawable hadithBg = new GradientDrawable();
        hadithBg.setColor(0x3310B981); // Emerald tint
        hadithBg.setCornerRadius(16);
        hadithBox.setBackground(hadithBg);

        TextView hadithTitle = new TextView(context);
        hadithTitle.setText("حديث اليوم المأثور:");
        hadithTitle.setTextSize(14);
        hadithTitle.setTextColor(0xFF34D399);
        hadithBox.addView(hadithTitle);

        TextView hadithView = new TextView(context);
        hadithView.setText(wisdom.hadithText + "\n[" + wisdom.hadithSource + "]");
        hadithView.setTextSize(16);
        hadithView.setTextColor(0xFFFFFFFF);
        hadithView.setPadding(0, 6, 0, 0);
        hadithBox.addView(hadithView);
        layout.addView(hadithBox);

        // Dua Scrollable Content
        ScrollView scroll = new ScrollView(context);
        scroll.setPadding(0, 16, 0, 16);
        TextView duaView = new TextView(context);
        duaView.setText(wisdom.duaContent);
        duaView.setTextSize(17);
        duaView.setTextColor(0xFFE2E8F0);
        duaView.setLineSpacing(6f, 1.2f);
        scroll.addView(duaView);
        layout.addView(scroll, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 420));

        builder.setView(layout);
        builder.setPositiveButton("إغلاق (تم)", (d, w) -> d.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
