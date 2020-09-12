package com.thoughtworks.basic;

import java.util.List;

public class Args {
    private List<Argument> arguments;

    public Args(List<Argument> arguments) {
        this.arguments = arguments;
    }

    Object getValueOf(String flag) {
        return arguments.stream()
                .filter(argument -> argument.getFlag().equalsIgnoreCase(flag))
                .findFirst()
                .get()
                .getValue();
    }
}
