package ru.chuvahina.context;

import ru.chuvahina.comparator.ComparatorFactory;
import ru.chuvahina.configuration.Configuration;
import ru.chuvahina.exception.FileReaderException;
import ru.chuvahina.node.IntegerNode;
import ru.chuvahina.node.Node;
import ru.chuvahina.reader.FileReader;
import ru.chuvahina.sorter.MergeSort;
import ru.chuvahina.writer.FileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;

public class IntegerContext extends Context<Integer> {
    public IntegerContext(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void build() throws IOException {
        writer = new FileWriter(configuration);
        Comparator<Integer> comparator = ComparatorFactory.create(configuration);
        Comparator<Node<Integer>> nodeComparator = ComparatorFactory.create(configuration);
        for (File file : configuration.getInputFiles()) {
            Node<Integer> node = new IntegerNode(new FileReader(file), comparator);
            if (node.hasNextValue()) {
                if (node.readLastValue() != null) {
                    nodes.add(node);
                } else {
                    node.getReader().close();
                }
            }
        }
        sorter = new MergeSort<>(nodeComparator);
    }
}
