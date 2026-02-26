package org.example.task;

import org.example.Film;

import java.util.HashMap;
import java.util.Map;

public class CountNumberGenres implements Calculus<Map<String, Long>> {

    private final Map<String, Long> countGenres = new HashMap<>();

    @Override
    public void calculate(Film film) {
        for (String genre : film.getGenres()) {
            countGenres.merge(genre, 1L, Long::sum);
        }
    }

    @Override
    public Map<String, Long> getResult() {
        return countGenres;
    }
}
