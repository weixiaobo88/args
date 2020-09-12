package com.thoughtworks.basic;

public class Argument {
    private final String flag;
    private final Object value;

    public Argument(String flag, Object value) {
        this.flag = flag;
        this.value = value;
    }

    Object getValue() {
        return value;
    }

    String getFlag() {
        return flag;
    }

    boolean instanceOf(SchemaDefinition schemaDefinition) {
        return schemaDefinition.isMatch(this.flag);
    }
}
