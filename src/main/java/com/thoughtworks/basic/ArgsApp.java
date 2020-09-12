package com.thoughtworks.basic;

import java.util.List;
import java.util.Map;

public class ArgsApp {
    Args generate(String schemaText, String argsText) {
        Map<String, String> keyValuePairs = new Lexer().scan(argsText);

        List<Argument> arguments = new ArgumentParser(buildSchema(schemaText)).parse(keyValuePairs);

        return new Args(arguments);
    }

    private Schema buildSchema(String schemaText) {
        SchemaParser schemaParser = new SchemaParser(schemaText);

        return new Schema(schemaParser.parse());
    }
}
