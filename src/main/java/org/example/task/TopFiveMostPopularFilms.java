package org.example.task;

import org.example.Film;

import java.util.Map;
import java.util.TreeMap;

public class TopFiveMostPopularFilms implements Calculus<Map<Float, Film>> {

    private final TreeMap<Float, Film> topFive = new TreeMap<>();

    @Override
    public void calculate(Film film) {
        float score = film.getImdbScore();

        if (!topFive.containsKey(score)) {

            topFive.put(score, film);

            if (topFive.size() > 5) {
                topFive.pollFirstEntry();
            }
        }
    }

    @Override
    public Map<Float, Film> getResult() {
        return topFive;
    }
}
