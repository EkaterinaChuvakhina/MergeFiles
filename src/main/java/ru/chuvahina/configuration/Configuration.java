package ru.chuvahina.configuration;


import ru.chuvahina.exception.ConfigurationException;
import java.io.File;
import java.util.List;

public class Configuration {
    private final DataType dataType;
    private final File outputFile;
    private final List<File> inputFiles;
    private final SortMode sortMode;

    public Configuration(SortMode sortMode, DataType dataType, File outputFile, List<File> inputFiles) throws ConfigurationException {
        this.sortMode = sortMode;
        this.dataType = dataType;
        this.outputFile = outputFile;
        this.inputFiles = inputFiles;
        if (dataType == null) {
            throw new ConfigurationException("Error! Data type not passed.");
        }
        if (outputFile == null) {
            throw new ConfigurationException("Error! Output file name not passed.");
        }
        if (inputFiles == null) {
            throw new ConfigurationException("Error! No input files passed.");
        }

    }

    public SortMode getSortMode() {
        return sortMode;
    }

    public DataType getDataType() {
        return dataType;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public List<File> getInputFiles() {
        return inputFiles;
    }
}
