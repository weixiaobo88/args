package com.thoughtworks.basic;

import com.thoughtworks.basic.exception.FlagNotDefinedException;

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

}
