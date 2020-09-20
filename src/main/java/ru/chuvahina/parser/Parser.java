package ru.chuvahina.parser;

import ru.chuvahina.configuration.Configuration;
import ru.chuvahina.configuration.DataType;
import ru.chuvahina.configuration.SortMode;
import ru.chuvahina.exception.ConfigurationException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    public static Configuration parse(String[] args) throws ConfigurationException {
        final List<String> MODES = new ArrayList<>(Arrays.asList("-a", "-d"));
        final List<String> TYPES = new ArrayList<>(Arrays.asList("-s", "-i"));

        SortMode sortMode = SortMode.ASC;
        DataType dataType = null;
        File output = null;
        List<File> input = null;

        if (args.length > 0) {
            int index = 0;
            if (MODES.contains(args[index])) {
                sortMode = SortMode.convertToSortMode(args[index]);
                index++;
            }

            if (index <= args.length - 1 && TYPES.contains(args[index])) {
                dataType = DataType.convertToDataType(args[index]);
            } else {
                throw new ConfigurationException("Error! Data type not passed or passed incorrect.");
            }

            if (++index <= args.length - 1) {
                output = new File(args[index]);
            }
            if (++index <= args.length - 1) {
                input = new ArrayList<>(Arrays.asList(args).subList(index, args.length)).stream().map(File::new).collect(Collectors.toList());
            }
        }
        return new Configuration(sortMode, dataType, output, input);
    }
}
