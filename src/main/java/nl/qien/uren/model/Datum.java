package nl.qien.uren.model;

import java.time.LocalDate;

public class Datum {

    public static int daysInMonth(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 01);
        int a = date.lengthOfMonth();
        return a;
    }

    public static int makeDaysOfMonthList(int year, int month) {
        int i = 1;
        LocalDate date = LocalDate.of(year, month, 1);
        int a = date.lengthOfMonth();
        for (; i <= a; i++) {
            LocalDate date1 = LocalDate.of(year, month, i);
            System.out.println("date.getDayOfMonth() = " + date1.getMonth() + " " + date1.getDayOfWeek() + " " + date1.getDayOfMonth());
        }
        return a;
    }
}
