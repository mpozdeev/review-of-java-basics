package org.example.task;

import org.example.Film;

import java.util.HashMap;
import java.util.Map;

public class CountLanguages implements Calculus<Map<String, Long>> {

    private final Map<String, Long> countLanguages = new HashMap<>();

    @Override
    public void calculate(Film film) {
        for (String language : film.getLanguages()) {
            countLanguages.merge(language, 1L, Long::sum);
        }
    }

    @Override
    public Map<String, Long> getResult() {
        return countLanguages;
    }
}
