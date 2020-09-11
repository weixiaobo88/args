package com.thoughtworks.basic;

import java.util.List;

public class Args {
    private final String inputCommand;
    private ArgumentParser argumentParser;

    public Args(Schema schema, String inputCommand) {
        this.inputCommand = inputCommand;
        argumentParser = new ArgumentParser(schema);
    }

    private List<Argument> analyze(String inputCommand) {
        List<ArgumentTO> argumentTOs = new Lexer().scan(inputCommand);

        return argumentParser.parse(argumentTOs);
    }

    public Object getValueOf(String flag) {
        List<Argument> arguments = analyze(inputCommand);

        return pickValueByFlag(flag, arguments);
    }

    private Object pickValueByFlag(String flag, List<Argument> arguments) {
        return arguments.stream()
                .filter(argument -> argument.getFlag().equalsIgnoreCase(flag))
                .findFirst()
                .get()
                .getValue();
    }
}
