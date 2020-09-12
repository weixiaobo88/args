package com.thoughtworks.basic;

public class StringValueHandler implements ValueHandler {
    @Override
    public Object handle(String value) {
        return value;
    }
}
