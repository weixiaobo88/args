package com.thoughtworks.basic;

import com.thoughtworks.basic.exception.FlagDuplicationException;

import java.util.ArrayList;
import java.util.List;

class ArgumentParser {
    private Schema schema;

    ArgumentParser(Schema schema) {
        this.schema = schema;
    }

    List<Argument> parse(List<ArgumentTO> argumentTOs) {
        List<Argument> arguments = new ArrayList<>();

        argumentTOs.forEach(argumentTO -> {
            Argument argument = generateArgument(argumentTO);
            validateArgument(arguments, argument);
            arguments.add(argument);
        });

        return arguments;
    }

    private void validateArgument(List<Argument> arguments, Argument argument) {
        boolean existSameFlag = arguments.stream()
                .anyMatch(existedArgument -> existedArgument.getFlag().equals(argument.getFlag()));

        if (existSameFlag) {
            throw new FlagDuplicationException();
        }
    }

    private Argument generateArgument(ArgumentTO argumentTO) {
        String flag = argumentTO.getFlag();
        String value = argumentTO.getValue();
        return new Argument(flag, parseValueType(schema, flag, value));
    }

    private Object parseValueType(Schema schema, String flag, String value) {
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
