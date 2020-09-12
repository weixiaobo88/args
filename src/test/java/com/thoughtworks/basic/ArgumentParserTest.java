package com.thoughtworks.basic;

import com.thoughtworks.basic.exception.FlagNotDefinedException;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ArgumentParserTest {
    private ArgumentParser argumentParser;

    @Before
    public void setUp() {
        //given
        argumentParser = new ArgumentParser(buildSchema());
    }

    @Test
    public void should_return_arguments_when_parse_given_key_value_pairs() {
        //when
        List<Argument> arguments = argumentParser.parse(buildNormalParserSource());

        //then
        assertEquals(3, arguments.size());
        assertEquals("l", arguments.get(0).getFlag());
        assertEquals(true, arguments.get(0).getValue());
    }


    @Test(expected = FlagNotDefinedException.class)
    public void should_throw_exception_when_parse_given_flag_not_exist_in_schema_definition() {
        //when
        argumentParser.parse(buildParserSourceWithUndefinedKey());
    }

    private Schema buildSchema() {
        Set<SchemaDefinition> schemaDefinitions = new HashSet<>();
        schemaDefinitions.add(new SchemaDefinition("l", ValueType.BOOLEAN));
        schemaDefinitions.add(new SchemaDefinition("p", ValueType.INTEGER));
        schemaDefinitions.add(new SchemaDefinition("d", ValueType.STRING));
        return new Schema(schemaDefinitions);
    }

    private HashMap<String, String> buildNormalParserSource() {
        HashMap<String, String> keyValuePairs = new LinkedHashMap<>();
        keyValuePairs.put("l", "true");
        keyValuePairs.put("p", "8080");
        keyValuePairs.put("d", "/usr/logs");

        return keyValuePairs;
    }

    private Map<String, String> buildParserSourceWithUndefinedKey() {
        HashMap<String, String> keyValuePairs = new LinkedHashMap<>();
        keyValuePairs.put("q", "8080");

        return keyValuePairs;
    }
}
