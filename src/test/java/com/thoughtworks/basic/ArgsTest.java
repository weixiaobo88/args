package com.thoughtworks.basic;

import com.thoughtworks.basic.exception.InvalidInputCommandException;
import com.thoughtworks.basic.exception.FlagDuplicationException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ArgsTest {
    private Args args;
    private Schema schema;

    @Before
    public void setUp() {
        //given
        Set<SchemaElement> schemaElements = new HashSet<>();
        schemaElements.add(new SchemaElement("l", Boolean.class));
        schemaElements.add(new SchemaElement("p", Integer.class));
        schemaElements.add(new SchemaElement("d", String.class));
        schema = new Schema(schemaElements);
    }

    @Test
    public void should_return_type_when_get_value_given_schema_and_parsed_string() {
        //when
        args = new Args(schema, "-l true -p 8080 -d /usr/logs");

        //then
        assertEquals(true, args.getValueOf("l"));
        assertEquals(8080, args.getValueOf("p"));
        assertEquals("/usr/logs", args.getValueOf("d"));
    }

    @Test
    public void should_return_default_type_when_get_value_given_schema_and_parsed_string_without_type() {
        //when
        args = new Args(schema, "-l -p 8080 -d /usr/logs");

        //then
        assertEquals(false, args.getValueOf("l"));
        assertEquals(8080, args.getValueOf("p"));
        assertEquals("/usr/logs", args.getValueOf("d"));
    }

    @Test(expected = FlagDuplicationException.class)
    public void should_throw_flag_duplication_exception_when_get_value_given_schema_and_parsed_string_with_duplicated_flag() {
        //when
        args = new Args(schema, "-p 8080 -p 2020");
        args.getValueOf("l");
    }

    @Test(expected = InvalidInputCommandException.class)
    public void should_throw_invalid_input_command_exception_when_get_value_given_schema_and_empty_string() {
        //when
        args = new Args(schema, "");
        args.getValueOf("l");
    }
}
