package ru.chuvahina.configuration;

import ru.chuvahina.exception.ConfigurationException;

public enum DataType {
    STRING("-s"), INTEGER("-i");

    String type;

    DataType(String type) {
        this.type = type;
    }

    public static DataType convertToDataType(String type) {
        for (DataType value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        throw new ConfigurationException("The passed data type is not supported " + type);
    }
}


