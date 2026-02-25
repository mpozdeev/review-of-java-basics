package org.example;

import com.opencsv.bean.AbstractBeanField;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenreCsvConverter extends AbstractBeanField<String, List<String>> {

    @Override
    protected List<String> convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return List.of();
        }
        return Arrays.stream(value.split("[/ -]"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
