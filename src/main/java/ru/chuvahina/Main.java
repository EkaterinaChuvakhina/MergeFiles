package ru.chuvahina;

import ru.chuvahina.configuration.Configuration;
import ru.chuvahina.context.Context;
import ru.chuvahina.context.IntegerContext;
import ru.chuvahina.context.StringContext;
import ru.chuvahina.exception.ConfigurationException;
import ru.chuvahina.exception.FileReaderException;
import ru.chuvahina.exception.OrderException;
import ru.chuvahina.exception.WriterException;
import ru.chuvahina.node.Node;
import ru.chuvahina.parser.Parser;
import ru.chuvahina.reader.FileReader;
import ru.chuvahina.sorter.Sort;
import ru.chuvahina.writer.Writer;

import java.io.IOException;
import java.util.List;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Configuration configuration;
        try {
            configuration = Parser.parse(args);
        } catch (ConfigurationException e) {
            System.out.println(e.getMessage());
            return;
        }

        Context context;
        switch (configuration.getDataType()) {
            case INTEGER:
                context = new IntegerContext(configuration);
                break;
            case STRING:
                context = new StringContext(configuration);
                break;
            default:
                throw new IllegalArgumentException();
        }
        try {
            context.build();
            List<Node<?>> nodes = context.getNodes();
            Sort<Node<?>> sorter = context.getSorter();
            try (Writer writer = context.getWriter()) {
                while (!nodes.isEmpty()) {
                    int indexMinNode = sorter.getNextSorted(nodes);
                    if (nodes.get(indexMinNode).getLastValue() != null) {
                        writer.write(nodes.get(indexMinNode).getLastValue());
                    }
                    nodes.get(indexMinNode).setPrevValue();

                    if (nodes.get(indexMinNode).readLastValue() == null) {
                        nodes.remove(indexMinNode);
                    }
                }
                System.out.println("File sorting completed successfully!");

            } catch (WriterException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            } finally {
                nodes.forEach(n -> {
                    try {
                        n.getReader().close();
                    } catch (IOException e) {
                        if (n.getReader() instanceof FileReader) {
                            System.out.println("Error closing reader for file: " + ((FileReader) (n.getReader())).getFile().getName());
                        }
                    }
                });
            }

        } catch (OrderException | FileReaderException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error! Unable to close file.");
        }
    }
}

