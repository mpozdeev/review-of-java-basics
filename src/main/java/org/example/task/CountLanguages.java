package org.example.task;

import org.example.Film;

import java.util.HashMap;
import java.util.Map;

public class CountLanguages implements Calculus<Film, Map<String, Long>> {

    private Map<String, Long> countLanguages = new HashMap<>();

    public CountLanguages() {
    }
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

    @Override
    public void print() {
        countLanguages.entrySet()
                .forEach(System.out::println);
    }
}
