package com.thoughtworks.basic;

import java.util.List;

public class Args {
    private Lexer lexer;
    private ArgumentParser argumentParser;

    public Args(Schema schema, Lexer lexer) {
        this.lexer = lexer;
        argumentParser = new ArgumentParser(schema);
    }

    List<Argument> analyze(String inputCommand) {
        List<ArgumentTO> argumentTOs = lexer.scan(inputCommand);

        return argumentParser.parse(argumentTOs);
    }
}
