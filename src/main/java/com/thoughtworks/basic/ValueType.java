package com.thoughtworks.basic;

public enum ValueType {
    BOOLEAN(false),
    INTEGER(0),
    STRING("");

    private Object defaultValue;

    ValueType(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public String getType() {
        return this.name().toLowerCase();
    }
}
