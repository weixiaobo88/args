package com.thoughtworks.basic;

import com.thoughtworks.basic.exception.EmptyStringException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LexerTest {
    @Test
    public void should_return_list_with_one_element_when_parse_given_one_flag_value() {
        //given
        Lexer lexer = new Lexer("-l true");

        //when
        List<String> result = lexer.parse();

        //then
        assertEquals(1, result.size());
        assertEquals("-l true", result.get(0));
    }

    @Test
    public void should_return_list_with_multiple_elements_when_parse_given_multiple_flag_value() {
        //given
        Lexer lexer = new Lexer("-l true -p 8080 -d /usr/logs");

        //when
        List<String> result = lexer.parse();

        //then
        assertEquals(3, result.size());
        assertEquals("-l true", result.get(0));
        assertEquals("-p 8080", result.get(1));
        assertEquals("-d /usr/logs", result.get(2));
    }

    @Test
    public void should_return_list_with_multiple_elements_when_parse_given_flag_with_value_is_empty() {
        //given
        Lexer lexer = new Lexer("-l -p 8080 -d /usr/logs");

        //when
        List<String> result = lexer.parse();

        //then
        assertEquals(3, result.size());
        assertEquals("-l", result.get(0));
        assertEquals("-p 8080", result.get(1));
        assertEquals("-d /usr/logs", result.get(2));
    }

    @Test
    public void should_return_list_when_parse_given_flag_value_with_empty_space_between() {
        //given
        Lexer lexer = new Lexer("    -l  true ");

        //when
        List<String> result = lexer.parse();

        //then
        assertEquals(1, result.size());
        assertEquals("-l  true", result.get(0));
    }

    @Test
    public void should_return_list_when_parse_given_flag_value_with_empty_space_at_start_and_end() {
        //given
        Lexer lexer = new Lexer("    -l  true ");

        //when
        List<String> result = lexer.parse();

        //then
        assertEquals(1, result.size());
        assertEquals("-l  true", result.get(0));
    }

    @Test(expected = EmptyStringException.class)
    public void should_throw_exception_when_parse_given_empty_string() {
        //given
        Lexer lexer = new Lexer(" ");

        //when
        lexer.parse();
    }
}