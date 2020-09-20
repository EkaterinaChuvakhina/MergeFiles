package ru.chuvahina.sorter;


import ru.chuvahina.heap.Heap;
import ru.chuvahina.node.Node;

import java.util.Comparator;
import java.util.List;

public class MergeSort<T extends Comparable<T>> implements Sort<Node<T>> {
    private final Comparator<Node<T>> nodeComparator;

    public MergeSort(Comparator<Node<T>> nodeComparator) {
        this.nodeComparator = nodeComparator;
    }

    @Override
    public int getNextSorted(List<Node<T>> nodes) {
        Heap<Node<T>> heap = new Heap<>(nodeComparator);
        heap.addAll(nodes);
        return nodes.indexOf(heap.poll());
    }
}

