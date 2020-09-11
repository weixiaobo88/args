package com.thoughtworks.basic;

import java.util.ArrayList;
import java.util.List;

class ArgumentParser {
    private Schema schema;

    ArgumentParser(Schema schema) {
        this.schema = schema;
    }

    List<Argument> parse(List<String> splitParts) {
        List<Argument> arguments = new ArrayList<>();

        splitParts.forEach(part -> {
            ArgumentTO argumentTO = pickArgumentTO(part);
            arguments.add(generateArgument(argumentTO));
        });

        return arguments;
    }

    private ArgumentTO pickArgumentTO(String part) {
        String[] split = part.split("\\s+");
        String flag = split[0].substring(1);

        if (split.length > 1) {
            String value = split[1];

            return new ArgumentTO(flag, value);
        }

        String defaultValue = "";
        return new ArgumentTO(flag, defaultValue);
    }

    private Argument generateArgument(ArgumentTO argumentTO) {
        String flag = argumentTO.getFlag();
        String value = argumentTO.getValue();
        return new Argument(flag, parseValueBy(schema, flag, value));
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
