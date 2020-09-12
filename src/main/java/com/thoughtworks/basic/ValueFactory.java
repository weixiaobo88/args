package com.thoughtworks.basic;

public class ValueFactory {
    public ValueHandler create(String flagType) {
        switch (flagType) {
            case "boolean":
                return new BooleanValueHandler();
            case "integer":
                return new IntegerValueHandler();
            default:
                return new StringValueHandler();
        }
    }
}
