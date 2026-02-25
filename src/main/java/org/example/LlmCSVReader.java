package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LlmCSVReader {

    private final Path filePath;

    public LlmCSVReader(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Возвращает поток строк из CSV-файла, не загружая весь файл в память.
     * Пропускает первую строку (заголовок), если нужно.
     */
    public Stream<String> readLines() {
        try {
            BufferedReader reader = Files.newBufferedReader(filePath);
            return StreamSupport.stream(
                    new Iterable<String>() {
                        @Override
                        public Iterator<String> iterator() {
                            return new Iterator<String>() {
                                private String nextLine;

                                {
                                    try {
                                        nextLine = reader.readLine(); // читаем первую строку (заголовок)
                                    } catch (IOException e) {
                                        nextLine = null;
                                    }
                                }

                                @Override
                                public boolean hasNext() {
                                    if (nextLine == null) {
                                        try {
                                            reader.close();
                                        } catch (IOException ignored) {}
                                        return false;
                                    }
                                    return true;
                                }

                                @Override
                                public String next() {
                                    if (!hasNext()) {
                                        throw new NoSuchElementException();
                                    }
                                    String current = nextLine;
                                    try {
                                        nextLine = reader.readLine();
                                    } catch (IOException e) {
                                        nextLine = null;
                                    }
                                    return current;
                                }
                            };
                        }
                    }.spliterator(),
                    false
            ).filter(line -> line != null && !line.trim().isEmpty()); // фильтруем пустые строки
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + filePath, e);
        }
    }
}
