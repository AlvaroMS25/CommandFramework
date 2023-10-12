package org.cmdfw.slash;

public class NameValue {
    private final String name;
    private final String value;

    public NameValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    String getName() {
        return name;
    }

    String getValue() {
        return value;
    }
}
