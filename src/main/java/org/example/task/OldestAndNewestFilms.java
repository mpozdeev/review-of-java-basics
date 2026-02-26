package org.example.task;

import org.example.Film;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class OldestAndNewestFilms implements Calculus<Map<String, LocalDate>> {

    private LocalDate oldestFilm = LocalDate.of(Year.MAX_VALUE, 12, 31);

    private LocalDate newestFilm = LocalDate.of(Year.MIN_VALUE, 1, 1);

    private final Map<String, LocalDate> result = new HashMap<>();

    @Override
    public void calculate(Film film) {
        LocalDate datePremiere = film.getPremiere();
        if (datePremiere.isAfter(newestFilm)) {
            newestFilm = datePremiere;
        }
        if (datePremiere.isBefore(oldestFilm)) {
            oldestFilm = datePremiere;
        }
    }

    @Override
    public Map<String, LocalDate> getResult() {
        result.put("newest", newestFilm);
        result.put("oldest", oldestFilm);
        return result;
    }
}
