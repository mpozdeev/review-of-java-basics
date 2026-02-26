package org.example;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MyCsvReader {

    private final String csvFileName;

    public MyCsvReader(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public Stream<Film> lineByLineReadingCsvFile() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFileName));
            CsvToBean<Film> csvToBean = new CsvToBeanBuilder<Film>(reader)
                    .withType(Film.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.stream()
                    .onClose(() -> {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }
}
