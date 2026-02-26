package org.example.task;

import org.example.Film;

public interface Calculus<R> {

    void calculate(Film film);

    R getResult();
}
