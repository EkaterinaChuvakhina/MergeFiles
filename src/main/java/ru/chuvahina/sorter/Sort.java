package ru.chuvahina.sorter;

import java.util.List;

public interface Sort<T> {
    int getNextSorted(List<T> nodes);
}
