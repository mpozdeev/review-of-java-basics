package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

//import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyCsvReader {

    private String csvFileName;

    private Map<String, Long> countGenres = new HashMap<>();
//    private Map<String, Long> countLanguages = new HashMap<>();
    private LocalDate oldestFilm = LocalDate.of(Year.MAX_VALUE, 12, 31);

    private LocalDate newestFilm = LocalDate.of(Year.MIN_VALUE, 1, 1);

    private int totalInMinutes = 0;

    private Map<Integer, RanksFilmsForYear> bestAndWorstFilmsByYear = new HashMap<>();


    public MyCsvReader(String fileName) {
        this.csvFileName = fileName;
        lineByLineReadingCsvFile();
    }


    private void lineByLineReadingCsvFile() {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFileName))) {
            CsvToBean<Film> csvToBean = new CsvToBeanBuilder<Film>(reader)
                    .withType(Film.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            csvToBean.stream()
                    .map(x -> x.getGenres())
                    .flatMap(List::stream)
                    .distinct()
                    .forEach(System.out::println);


            for (Film film : csvToBean) {
//                System.out.println(film);
                countNumberGenres(film);
//                countNumberLanguages(film);
                oldestAndNewestFilms(film);
                totalDurationInMinutes(film);
                bestAndWorstFilmsOfYear(film);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printBestAndWorstFilmsByYear() {
        bestAndWorstFilmsByYear.entrySet().stream()
                .forEach(e -> System.out.println(
                        e.getKey() +
                                "\tхудший: " + e.getValue().getWorstRank() + " " + e.getValue().getWorstFilm().getTitle() + "\n" +
                                "\t\tлучший: " + e.getValue().getBestRank() + " " + e.getValue().getBestFilm().getTitle()
                ));
    }

    public void totalDurationInMinutes() {
        int minutesOnHours = 60;
        int minutesOnDay = 1440; // 60*24
//        System.out.println("Total minutes: " + totalDurationInMinutes);
        System.out.println("Total hours: " + totalInMinutes / minutesOnHours);
        System.out.println("Total days: " + totalInMinutes / minutesOnDay);
    }

    public void printOldestAndNewestFilms() {
        System.out.println("Oldest Film: " + oldestFilm);
        System.out.println("Newest Film: " + newestFilm);
    }

    public void printCountGenres() {
        countGenres.entrySet()
                .forEach(System.out::println);
    }

//    public void printCountLanguages() {
//        countLanguages.entrySet()
//                .forEach(System.out::println);
//    }


    private void bestAndWorstFilmsOfYear(Film film) {
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


    private void totalDurationInMinutes(Film film) {
        totalInMinutes += film.getRuntime();
    }


    private void countNumberGenres(Film film) {
        for (String genre : film.getGenres()) {
            countGenres.merge(genre, 1L, Long::sum);
        }
    }

//    private void countNumberLanguages(Film film) {
//        for (String language : film.getLanguages()) {
//            countLanguages.merge(language, 1L, Long::sum);
//        }
//    }

    private void oldestAndNewestFilms(Film film) {
        LocalDate datePremiere = film.getPremiere();
        if (datePremiere.isAfter(newestFilm)) {
            newestFilm = datePremiere;
        }
        if (datePremiere.isBefore(oldestFilm)) {
            oldestFilm = datePremiere;
        }
    }

    //-------------------------------------------------------------------------
    public void readCsvFileOne(String csvFileName) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFileName))) {

            CsvToBean<Film> csvToBean = new CsvToBeanBuilder<Film>(reader)
                    .withType(Film.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Film> films = csvToBean.parse();

//            films.forEach(System.out::println);

            Map<String, Long> countGenre = films.stream()
                    .flatMap(film -> film.getGenres().stream())
                    .collect(Collectors.groupingBy(
                            genre -> genre,
                            Collectors.counting()
                    ));

            countGenre.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry ->
                            System.out.println(entry.getKey() + ": " + entry.getValue()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void readCsvFileTwo(String csvFileName) {
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFileName))
                .withSkipLines(0)
                .build()) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                for (String st : nextLine) {
                    System.out.println(st);
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
