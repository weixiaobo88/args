package com.thoughtworks.basic;

import java.util.*;

public class SchemaParser {
    private String schemaText;

    SchemaParser(String schemaText) {
        this.schemaText = schemaText;
    }

    Set<SchemaDefinition> parse() {
        String SCHEMA_DELIMITER = " ";
        String[] split = schemaText.split(SCHEMA_DELIMITER);

        Set<SchemaDefinition> schemaDefinitions = new HashSet<>();
        Arrays.asList(split)
                .forEach(schema -> schemaDefinitions.add(parseSchemaElement(schema)));

        return schemaDefinitions;
    }

    private SchemaDefinition parseSchemaElement(String schema) {
        String SCHEMA_ELEMENT_DELIMITER = ":";
        String[] split = schema.split(SCHEMA_ELEMENT_DELIMITER);
        String flag = split[0];
        String type = split[1];

        return new SchemaDefinition(flag, findMatchedTypeBy(type));
    }

    private ValueType findMatchedTypeBy(String type) {
        switch (type) {
            case "boolean":
                return ValueType.BOOLEAN;
            case "integer":
                return ValueType.INTEGER;
            default:
                return ValueType.STRING;
        }
    }
}
