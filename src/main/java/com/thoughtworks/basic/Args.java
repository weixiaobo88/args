package com.thoughtworks.basic;

import java.util.ArrayList;
import java.util.List;

public class Args {
    private final Schema schema;
    private Parser parser;

    public Args(Schema schema, Parser parser) {
        this.schema = schema;
        this.parser = parser;
    }

    public List<Argument> analyze() {
        List<String> parsedString = parser.parse();
        List<Argument> arguments = new ArrayList<>();
        parsedString.forEach(item -> {
            String[] split = item.split("\\s+");
            String flag = split[0].substring(1);
            String value = split[1];
            arguments.add(new Argument(flag, parseValueBy(schema, flag, value)));

        });

        return arguments;
    }

    private Object parseValueBy(Schema schema, String flag, String value) {
        Object flagType = schema.getFlagType(flag);

        if (flagType.equals(Boolean.class)) {
            return Boolean.parseBoolean(value);
        }

        if (flagType.equals(Integer.class)) {
            return Integer.parseInt(value);
        }

        return value;
    }
}
