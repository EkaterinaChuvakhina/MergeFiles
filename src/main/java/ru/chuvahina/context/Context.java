package ru.chuvahina.context;

import ru.chuvahina.configuration.Configuration;
import ru.chuvahina.node.Node;
import ru.chuvahina.sorter.Sort;
import ru.chuvahina.writer.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Context<T extends Comparable<T>> {
    protected Writer writer;
    protected List<Node<T>> nodes = new ArrayList<>();
    protected Sort<Node<T>> sorter;
    protected Configuration configuration;

    public Context(Configuration configuration) {
        this.configuration = configuration;
    }

    abstract public void build() throws IOException;

    public Writer getWriter() {
        return writer;
    }

    public List<Node<T>> getNodes() {
        return nodes;
    }

    public Sort<Node<T>> getSorter() {
        return sorter;
    }
}
