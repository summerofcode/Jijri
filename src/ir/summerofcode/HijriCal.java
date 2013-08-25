package ir.summerofcode;

public class HijriCal {
    
    public static int[] HijriToGregorian(int year, int month, int date) {
        // calculation
        int[] gDaysInMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] jDaysInMonth = new int[]{31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
        int jy = year - 979;
        int jm = month - 1;
        int jd = date - 1;
        int gm, gd, gy;
        int jDayNo = 365 * jy + (jy / 33) * 8 + ((jy % 33) + 3) / 4;
        for (int i = 0; i < jm; i++)
            jDayNo += jDaysInMonth[i];
        jDayNo += jd;
        int gDayNo = jDayNo + 79;
        gy = 1600 + 400 * (gDayNo / 146097);
        gDayNo = gDayNo % 146097;
        boolean leap = true;
        if (gDayNo >= 36525) {
            gDayNo--;
            gy += 100 * (gDayNo / 36524);
            gDayNo = gDayNo % 36524;
            if (gDayNo >= 365)
                gDayNo = gDayNo + 1;
            else
                leap = false;
        }
        gy += 4 * (gDayNo / 1461);
        gDayNo %= 1461;
        if (gDayNo >= 366) {
            leap = false;
            gDayNo--;
            gy += gDayNo / 365;
            gDayNo = gDayNo % 365;
        }
        int i = 0;
        int tmp = 0;
        while (gDayNo >= (gDaysInMonth[i] + tmp)) {
            if (i == 1 && leap == true)
                tmp = 1;
            else
                tmp = 0;
            gDayNo -= gDaysInMonth[i] + tmp;
            i++;
        }
        gm = i + 1;
        gd = gDayNo + 1;
        
        // result
        int[] result = new int[3];
        result[0] = gy;
        result[1] = gm;
        result[2] = gd;
        return result;
    }
    
    public static int[] GregorianToHijri(int year, int month, int date) {
        // calculation
        int[] gDaysInMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] jDaysInMonth = new int[]{31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
        int gy = year - 1600;
        int gm = month - 1;
        int gd = date - 1;
        int jm, jd, jy;
        int gDayNo = 365 * gy + (gy + 3) / 4 - (gy + 99) / 100 + (gy + 399) / 400;
        for (int i = 0; i < gm; i++)
            gDayNo += gDaysInMonth[i];
        if (gm > 1 && (gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0))
            gDayNo++;
        gDayNo += gd;
        int jDayNo = gDayNo - 79;
        int jNp = jDayNo / 12053;
        jDayNo = jDayNo % 12053;
        jy = 979 + 33 * jNp + 4 * (jDayNo / 1461);
        jDayNo %= 1461;
        if (jDayNo >= 366) {
            jy += (jDayNo - 1) / 365;
            jDayNo = (jDayNo - 1) % 365;
        }
        int i = 0;
        while (i < 11 && jDayNo >= jDaysInMonth[i]) {
            jDayNo -= jDaysInMonth[i];
            i++;
        }
        jm = i + 1;
        jd = jDayNo + 1;

        // result
        int[] result = new int[3];
        result[0] = jy;
        result[1] = jm;
        result[2] = jd;
        return result;
    }
    
}
