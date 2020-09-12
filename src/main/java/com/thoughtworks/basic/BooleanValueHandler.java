package com.thoughtworks.basic;

public class BooleanValueHandler implements ValueHandler {
    @Override
    public Object handle(String value) {
        return Boolean.parseBoolean(value);
    }
}
