package com.thoughtworks.basic;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SchemaParserTest {
    @Test
    public void should_return_parsed_schema_element_when_parse_given_one_schema_string() {
        //given
        SchemaParser schemaParser = new SchemaParser("l:boolean");

        //when
        Set<SchemaDefinition> schemaDefinitions = schemaParser.parse();

        //then
        assertEquals(1, schemaDefinitions.size());
        assertTrue(schemaDefinitions.contains(new SchemaDefinition("l", ValueType.BOOLEAN)));
    }

    @Test
    public void should_return_parsed_schema_elements_when_parse_given_multiple_schema_string() {
        //given
        SchemaParser schemaParser = new SchemaParser("l:boolean p:integer d:string");

        //when
        Set<SchemaDefinition> schemaDefinitions = schemaParser.parse();

        //then
        assertEquals(3, schemaDefinitions.size());
        assertTrue(schemaDefinitions.contains(new SchemaDefinition("l", ValueType.BOOLEAN)));
        assertTrue(schemaDefinitions.contains(new SchemaDefinition("p", ValueType.INTEGER)));
        assertTrue(schemaDefinitions.contains(new SchemaDefinition("d", ValueType.STRING)));
    }
}
