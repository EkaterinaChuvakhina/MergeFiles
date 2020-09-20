package ru.chuvahina.configuration;

public enum SortMode {
    ASC("-a"), DESC("-d");
    String mode;

    SortMode(String mode) {
        this.mode = mode;
    }

    public static SortMode convertToSortMode(String mode) {
        for (SortMode value : SortMode.values()) {
            if (value.mode.equals(mode)) {
                return value;
            }
        }
        throw new RuntimeException("Unknown sort mode passed " + mode);
    }

}
