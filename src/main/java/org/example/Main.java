package org.example;

import org.example.task.Calculus;
import org.example.task.CountLanguages;

import java.util.Map;

public class Main {

    /**
     * Есть CSV файл ( https://www.kaggle.com/datasets/luiscorter/netflix-original-films-imdb-scores), требуется
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
        MyCsvReader reader = new MyCsvReader(csvFileName);

//      Jenkins -> github -> webhook Testing6


        Calculus<Film, Map<String, Long>> countLanguages = new CountLanguages();
//        reader.readCsvFileOne(csvFileName);
//        reader.lineByLineReadingCsvFile(csvFileName);

//        reader.printCountGenres();
        System.out.println("----------------------------------");

//        reader.printCountLanguages();
        countLanguages.print();
        System.out.println("----------------------------------");

//        reader.printOldestAndNewestFilms();
        System.out.println("----------------------------------");

//        reader.totalDurationInMinutes();
        System.out.println("----------------------------------");

//        reader.printBestAndWorstFilmsByYear();
        System.out.println("----------------------------------");
    }
}