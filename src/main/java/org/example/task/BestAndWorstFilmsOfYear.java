package org.example.task;

import org.example.Film;
import org.example.RanksFilmsForYear;

import java.util.HashMap;
import java.util.Map;

public class BestAndWorstFilmsOfYear implements Calculus<Map<Integer, RanksFilmsForYear>> {

    private final Map<Integer, RanksFilmsForYear> bestAndWorstFilmsByYear = new HashMap<>();

    @Override
    public void calculate(Film film) {
        Integer keyYear = film.getPremiere().getYear();
        float imdbScore = film.getImdbScore();
        if (bestAndWorstFilmsByYear.containsKey(keyYear)) {
            RanksFilmsForYear ranksFilms = bestAndWorstFilmsByYear.get(keyYear);
            if (ranksFilms.getWorstRank() > imdbScore) {
                ranksFilms.setWorstRank(imdbScore);
                ranksFilms.setWorstFilm(film);
            }
            if (ranksFilms.getBestRank() < imdbScore) {
                ranksFilms.setBestRank(imdbScore);
                ranksFilms.setBestFilm(film);
            }
        } else {
            RanksFilmsForYear rankFilm = new RanksFilmsForYear();
            rankFilm.setYear(keyYear);
            rankFilm.setWorstRank(imdbScore);
            rankFilm.setWorstFilm(film);
            rankFilm.setBestRank(imdbScore);
            rankFilm.setBestFilm(film);
            bestAndWorstFilmsByYear.put(keyYear, rankFilm);
        }
    }

    @Override
    public Map<Integer, RanksFilmsForYear> getResult() {
        return bestAndWorstFilmsByYear;
    }

    public void printBestAndWorstFilmsByYear() {
        bestAndWorstFilmsByYear.forEach((key, value) -> System.out.println(
                key +
                        "\tхудший: " + value.getWorstRank() + " " + value.getWorstFilm().getTitle() + "\n" +
                        "\t\tлучший: " + value.getBestRank() + " " + value.getBestFilm().getTitle()
        ));
    }
}
