package com.thoughtworks.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ArgumentParser {
    private Schema schema;

    ArgumentParser(Schema schema) {
        this.schema = schema;
    }

    List<Argument> parse(Map<String, String> keyValuePairs) {
        List<Argument> argumentsFromKeyValuePairs = parseKeyValuePairs(schema, keyValuePairs);
        List<Argument> argumentsWithDefaultValue = schema.assignDefault(argumentsFromKeyValuePairs);

        return Stream.concat(argumentsFromKeyValuePairs.stream(), argumentsWithDefaultValue.stream())
            .collect(Collectors.toList());
    }

    private List<Argument> parseKeyValuePairs(Schema schema, Map<String, String> keyValuePairs) {
        List<Argument> arguments = new ArrayList<>();

        for (Map.Entry<String, String> keyValuePair : keyValuePairs.entrySet()) {
            String key = keyValuePair.getKey();
            String value = keyValuePair.getValue();

            Argument argument = generateArgument(schema, key, value);

            arguments.add(argument);
        }
        return arguments;
    }

    private Argument generateArgument(Schema schema, String flag, String value) {
        return new Argument(flag, parseValue(schema, flag, value));
    }

    private Object parseValue(Schema schema, String flag, String value) {
        String flagType = schema.getTypeOf(flag);
        ValueFactory factory = new ValueFactory();
        ValueHandler valueHandler = factory.create(flagType);
        return valueHandler.handle(value);
    }
}
