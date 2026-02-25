package org.example.task;

public interface Calculus<T, U> {

    void calculate(T t);

    U getResult();

    void print();
}
