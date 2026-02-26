package org.example;

import org.example.task.BestAndWorstFilmsOfYear;
import org.example.task.CountLanguages;
import org.example.task.CountNumberGenres;
import org.example.task.OldestAndNewestFilms;
import org.example.task.TopFiveMostPopularFilms;
import org.example.task.TotalDurationInMinutes;

public class Main {

    /**
     * Есть CSV файл ( <a href="https://www.kaggle.com/datasets/luiscorter/netflix-original-films-imdb-scores">https://www.kaggle.com/datasets/luiscorter/netflix-original-films-imdb-scores</a>), требуется
     * вычитать его и произвести следующие операции:
     * - Посчитать кол-во фильмов каждого жанра и языка
     * - Вывести самый новый и самый старый фильм
     * - Вывести общую длительность фильмов в часах и днях.
     * - Составить топ-5 самых популярных
     * - Для каждого года вывести самый высоко оцененный и самый низко оцененный фильм
     * <p>
     * Необходимо читать с диска и проходиться по фильмам один раз O(n)
     */

    public static void main(String[] args) {
        String csvFileName = "NetflixOriginals.csv";
        MyCsvReader myCsvReader = new MyCsvReader(csvFileName);

        var countLanguages = new CountLanguages();
        var countGenres = new CountNumberGenres();
        var twoFilms = new OldestAndNewestFilms();
        var bestAndWorstFilmsOfYear = new BestAndWorstFilmsOfYear();
        var totalDuration = new TotalDurationInMinutes();
        var topFive = new TopFiveMostPopularFilms();

        FilmProcessor filmProcessor = new FilmProcessor(myCsvReader,
                countLanguages,
                countGenres,
                twoFilms,
                bestAndWorstFilmsOfYear,
                totalDuration,
                topFive
        );

        filmProcessor.process();

        System.out.println("----------- count languages -----------------------");
        countLanguages.getResult().entrySet().forEach(System.out::println);

        System.out.println("----------- count genres -----------------------");
        countGenres.getResult().entrySet().forEach(System.out::println);

        System.out.println("----------------------------------");
        System.out.println("Oldest Film: " + twoFilms.getResult().get("newest"));
        System.out.println("Newest Film: " + twoFilms.getResult().get("oldest"));

        System.out.println("---------Best And Worst Films By Year ---------------");
        bestAndWorstFilmsOfYear.printBestAndWorstFilmsByYear();

        System.out.println("----------Total Duration In Minutes ------------------------");
        totalDuration.printTotalDurationInMinutes();

        System.out.println("----------Top-5 Most Popular Films ------------------------");
        topFive.getResult().forEach((imdbScore, film) ->
                System.out.println(imdbScore + " -> " + film.getTitle()));
    }
}