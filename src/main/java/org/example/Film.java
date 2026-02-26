package org.example;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import org.example.converter.GenreCsvConverter;
import org.example.converter.LocalDateMultiCsvConverter;
import org.example.converter.LanguagesCsvConverter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Film {

    public Film() {
    }

    @CsvBindByName(column = "Title")
    private String title;

    @CsvCustomBindByName(column = "Genre", converter = GenreCsvConverter.class)
    private List<String> genres;

    @CsvCustomBindByName(column = "Premiere", converter = LocalDateMultiCsvConverter.class)
    private LocalDate premiere;

    @CsvBindByName(column = "Runtime")
    private Integer runtime;

    @CsvBindByName(column = "IMDB Score")
    private float imdbScore;

    @CsvCustomBindByName(column = "Language", converter = LanguagesCsvConverter.class)
    private List<String> languages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }


    public LocalDate getPremiere() {
        return premiere;
    }

    public void setPremiere(LocalDate premiere) {
        this.premiere = premiere;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public float getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(float imdbScore) {
        this.imdbScore = imdbScore;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Float.compare(imdbScore, film.imdbScore) == 0 && Objects.equals(title, film.title) && Objects.equals(genres, film.genres) && Objects.equals(premiere, film.premiere) && Objects.equals(runtime, film.runtime) && Objects.equals(languages, film.languages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genres, premiere, runtime, imdbScore, languages);
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", genres=" + genres +
                ", premiere=" + premiere +
                ", runtime=" + runtime +
                ", imdbScore=" + imdbScore +
                ", languages=" + languages +
                '}';
    }
}