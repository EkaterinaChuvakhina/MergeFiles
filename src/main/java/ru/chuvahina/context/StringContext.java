package ru.chuvahina.context;

import ru.chuvahina.comparator.ComparatorFactory;
import ru.chuvahina.configuration.Configuration;
import ru.chuvahina.node.Node;
import ru.chuvahina.node.StringNode;
import ru.chuvahina.reader.FileReader;
import ru.chuvahina.sorter.MergeSort;
import ru.chuvahina.writer.FileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;

public class StringContext extends Context<String> {
    public StringContext(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void build() throws IOException {
        writer = new FileWriter(configuration);
        Comparator<String> comparator = ComparatorFactory.create(configuration);
        Comparator<Node<String>> nodeComparator = ComparatorFactory.create(configuration);
        for (File file : configuration.getInputFiles()) {
            Node<String> node = new StringNode(new FileReader(file), comparator);
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
