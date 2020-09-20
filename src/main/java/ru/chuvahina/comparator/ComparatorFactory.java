package ru.chuvahina.comparator;

import ru.chuvahina.configuration.Configuration;

import java.util.Comparator;

public class ComparatorFactory<T> {
    public static <T extends Comparable<T>> Comparator<T> create(Configuration configuration) {
        switch (configuration.getSortMode()) {
            case DESC:
                return new DescComparator<>();
            case ASC:
            default:
                return new AscComparator<>();

        }
    }
}
