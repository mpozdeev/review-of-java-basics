package org.example;

import java.util.Objects;

public class RanksFilmsForYear {

    private int year;
    private float bestRank;
    private float worstRank;
    private Film bestFilm;
    private Film worstFilm;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getBestRank() {
        return bestRank;
    }

    public void setBestRank(float bestRank) {
        this.bestRank = bestRank;
    }

    public float getWorstRank() {
        return worstRank;
    }

    public void setWorstRank(float worstRank) {
        this.worstRank = worstRank;
    }

    public Film getBestFilm() {
        return bestFilm;
    }

    public void setBestFilm(Film bestFilm) {
        this.bestFilm = bestFilm;
    }

    public Film getWorstFilm() {
        return worstFilm;
    }

    public void setWorstFilm(Film worstFilm) {
        this.worstFilm = worstFilm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RanksFilmsForYear that = (RanksFilmsForYear) o;
        return year == that.year && bestRank == that.bestRank && worstRank == that.worstRank && Objects.equals(bestFilm, that.bestFilm) && Objects.equals(worstFilm, that.worstFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, bestRank, worstRank, bestFilm, worstFilm);
    }

    @Override
    public String toString() {
        return "RanksFilmsForYear{" +
                "year=" + year +
                ", bestRank=" + bestRank +
                ", worstRank=" + worstRank +
                ", bestFilm=" + bestFilm +
                ", worstFilm=" + worstFilm +
                '}';
    }
}
