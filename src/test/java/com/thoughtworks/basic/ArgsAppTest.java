package com.thoughtworks.basic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgsAppTest {
    @Test
    public void should_return_args_when_generate_given_args_string() {
        //given
        ArgsApp argsApp = new ArgsApp();

        //when
        Args args = argsApp.generate("l:boolean p:integer d:string", "-l true -p 8080 -d /usr/logs");

        //then
        assertEquals(true, args.getValueOf("l"));
        assertEquals(8080, args.getValueOf("p"));
        assertEquals("/usr/logs", args.getValueOf("d"));
    }
}
