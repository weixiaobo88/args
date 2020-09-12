package com.thoughtworks.basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArgsTest {
    @Test
    public void should_return_type_when_get_value_given_arguments() {
        //given
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new Argument("l", true));
        arguments.add(new Argument("p", 8080));
        arguments.add(new Argument("d", "/usr/logs"));

        //when
        Args args = new Args(arguments);

        //then
        assertEquals(true, args.getValueOf("l"));
        assertEquals(8080, args.getValueOf("p"));
        assertEquals("/usr/logs", args.getValueOf("d"));
    }
}
