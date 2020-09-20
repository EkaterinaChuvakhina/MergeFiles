package ru.chuvahina.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<T extends Comparable<T>> {
    private final Comparator<T> comparator;
    private List<T> heap;

    public Heap(Comparator<T> comparator) {
        heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public Heap(Comparator<T> comparator, int capacity) {
        this(comparator);
        heap = new ArrayList<>(capacity);
    }

    private int parentIndex(int index) {
        if (index == 0) {
            return 0;
        }
        return (index - 1) / 2;
    }

    private int leftIndex(int index) {
        return 2 * index + 1;
    }

    private int rightIndex(int index) {
        return 2 * index + 2;
    }

    private void swap(int firstIndex, int secondIndex) {
        T temp = heap.get(firstIndex);
        heap.set(firstIndex, heap.get(secondIndex));
        heap.set(secondIndex, temp);
    }

    private void heapifyUp(int index) {
        if (index > 0 && comparator.compare(heap.get(parentIndex(index)), heap.get(index)) > 0) {
            swap(index, parentIndex(index));
            heapifyUp(parentIndex(index));
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void add(T element) {
        heap.add(element);
        int currentIndex = heap.size() - 1;
        heapifyUp(currentIndex);
    }

    public T poll() {
        if (heap.size() == 0) {
            throw new RuntimeException("Poll on empty heap!");
        }
        T root = heap.get(0);

        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        heapifyDown(0);

        return root;
    }

    private void heapifyDown(int index) {
        int leftChildIndex = leftIndex(index);
        int rightChildIndex = rightIndex(index);

        int needed = index;

        if (leftChildIndex < (heap.size() - 1) && comparator.compare(heap.get(leftChildIndex), heap.get(index)) > 0) {
            needed = leftChildIndex;
        }
        if (rightChildIndex < (heap.size() - 1) && comparator.compare(heap.get(rightChildIndex), heap.get(index)) > 0) {
            needed = rightChildIndex;
        }

        if (needed != index) {
            swap(index, needed);
            heapifyDown(needed);
        }
    }

    public void addAll(List<T> elements) {
        for (T element : elements) {
            add(element);
        }
    }
}
