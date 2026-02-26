package org.example.task;

import org.example.Film;

public class TotalDurationInMinutes implements Calculus<Integer> {

    private int totalInMinutes = 0;

    @Override
    public void calculate(Film film) {
        totalInMinutes += film.getRuntime();
    }

    @Override
    public Integer getResult() {
        return totalInMinutes;
    }

    public void printTotalDurationInMinutes() {
        int minutesOnHours = 60;
        int minutesOnDay = 1440; // 60*24
        System.out.println("Total hours: " + totalInMinutes / minutesOnHours);
        System.out.println("Total days: " + totalInMinutes / minutesOnDay);
    }
}
