package com.thoughtworks.basic;

public class IntegerValueHandler implements ValueHandler {
    @Override
    public Object handle(String value) {
        return Integer.parseInt(value);
    }
}
