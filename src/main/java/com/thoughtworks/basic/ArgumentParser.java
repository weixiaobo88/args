package com.thoughtworks.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ArgumentParser {
    private Schema schema;

    ArgumentParser(Schema schema) {
        this.schema = schema;
    }

    List<Argument> parse(Map<String, String> keyValuePairs) {
        List<Argument> arguments = new ArrayList<>();

        for (Map.Entry<String, String> keyValuePair : keyValuePairs.entrySet()) {
            String key = keyValuePair.getKey();
            String value = keyValuePair.getValue();

            Argument argument = generateArgument(key, value);

            arguments.add(argument);
        }

        return arguments;
    }

    private Argument generateArgument(String flag, String value) {
        return new Argument(flag, parseValue(schema, flag, value));
    }

    private Object parseValue(Schema schema, String flag, String value) {
        String flagType = schema.getTypeOf(flag);

        switch (flagType) {
            case "boolean":
                return Boolean.parseBoolean(value);
            case "integer":
                return Integer.parseInt(value);
            default:
                return value;
        }
    }
}
