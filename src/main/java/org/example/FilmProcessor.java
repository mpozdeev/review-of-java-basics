package org.example;

import org.example.task.BestAndWorstFilmsOfYear;
import org.example.task.CountLanguages;
import org.example.task.CountNumberGenres;
import org.example.task.OldestAndNewestFilms;
import org.example.task.TopFiveMostPopularFilms;
import org.example.task.TotalDurationInMinutes;

import java.util.stream.Stream;

public class FilmProcessor {

    private final MyCsvReader csvReader;
    private final CountLanguages countLanguages;
    private final CountNumberGenres countGenres;
    private final OldestAndNewestFilms twoFilms;
    private final BestAndWorstFilmsOfYear bestAndWorstFilmsOfYear;
    private final TotalDurationInMinutes totalDurationInMinutes;
    private final TopFiveMostPopularFilms topFivePopularFilms;

    public FilmProcessor(MyCsvReader csvReader,
                         CountLanguages countLanguages,
                         CountNumberGenres countGenres,
                         OldestAndNewestFilms twoFilms,
                         BestAndWorstFilmsOfYear bestAndWorstFilmsOfYear,
                         TotalDurationInMinutes totalDurationInMinutes,
                         TopFiveMostPopularFilms topFivePopularFilms) {
        this.csvReader = csvReader;
        this.countLanguages = countLanguages;
        this.countGenres = countGenres;
        this.twoFilms = twoFilms;
        this.bestAndWorstFilmsOfYear = bestAndWorstFilmsOfYear;
        this.totalDurationInMinutes = totalDurationInMinutes;
        this.topFivePopularFilms = topFivePopularFilms;
    }

    public void process() {
        try (Stream<Film> streamOfFilms = csvReader.lineByLineReadingCsvFile()) {
            var iterator = streamOfFilms.iterator();
            while (iterator.hasNext()) {
                Film film = iterator.next();
                countLanguages.calculate(film);
                countGenres.calculate(film);
                twoFilms.calculate(film);
                bestAndWorstFilmsOfYear.calculate(film);
                totalDurationInMinutes.calculate(film);
                topFivePopularFilms.calculate(film);
            }
        }
    }
}
