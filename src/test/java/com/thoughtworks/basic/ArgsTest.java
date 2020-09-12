package com.thoughtworks.basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArgsTest {
    private Args args;
    private List<Argument> arguments = new ArrayList<>();

    @Test
    public void should_return_type_when_get_value_given_arguments() {
        //when
        arguments.add(new Argument("l", true));
        arguments.add(new Argument("p", 8080));
        arguments.add(new Argument("d", "/usr/logs"));
        args = new Args(arguments);

        //then
        assertEquals(true, args.getValueOf("l"));
        assertEquals(8080, args.getValueOf("p"));
        assertEquals("/usr/logs", args.getValueOf("d"));
    }

    @Test
    public void should_return_default_type_when_get_value_given_schema_and_parsed_string_without_type() {
        //when
        arguments.add(new Argument("l", false));
        arguments.add(new Argument("p", 8080));
        arguments.add(new Argument("d", "/usr/logs"));
        args = new Args(arguments);

        //then
        assertEquals(false, args.getValueOf("l"));
        assertEquals(8080, args.getValueOf("p"));
        assertEquals("/usr/logs", args.getValueOf("d"));
    }
}
