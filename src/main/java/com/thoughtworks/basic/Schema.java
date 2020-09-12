package com.thoughtworks.basic;

import com.thoughtworks.basic.exception.FlagNotDefinedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Schema {
    private Set<SchemaDefinition> schemaDefinitions;

    public Schema(Set<SchemaDefinition> schemaDefinitions) {
        this.schemaDefinitions = schemaDefinitions;
    }

    String getTypeOf(String flag) {
        return schemaDefinitions.stream()
                .filter(element -> element.getFlag().equals(flag))
                .findFirst()
                .orElseThrow(FlagNotDefinedException::new)
                .getType();
    }

    public List<Argument> assignDefault(List<Argument> arguments) {
        List<Argument> argumentsWithDefaultValue = new ArrayList<>();

        schemaDefinitions.forEach(definition -> {
            if (arguments.stream().noneMatch(argument -> argument.instanceOf(definition))) {
                argumentsWithDefaultValue.add(new Argument(definition.getFlag(), definition.getDefaultValue()));
            }
        });

        return argumentsWithDefaultValue;
    }
}
