package com.thoughtworks.basic;

import com.thoughtworks.basic.exception.InvalidInputException;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LexerTest {
    private Map<String, String> parseByLexer(String source) {
        //given
        Lexer lexer = new Lexer();

        //when
        return lexer.scan(source);
    }

    @Test
    public void should_return_list_with_one_element_when_parse_given_one_flag_value() {
        Map<String, String> keyValuePairs = parseByLexer("-l true");

        //then
        assertEquals(1, keyValuePairs.size());
        assertEquals("true", keyValuePairs.get("l"));
    }

    @Test
    public void should_return_list_with_multiple_elements_when_parse_given_multiple_flag_value() {
        //given
        Map<String, String> keyValuePairs = parseByLexer("-l true -p 8080 -d /usr/logs");

        //then
        assertEquals(3, keyValuePairs.size());
        assertEquals("true", keyValuePairs.get("l"));
        assertEquals("8080", keyValuePairs.get("p"));
        assertEquals("/usr/logs", keyValuePairs.get("d"));
    }

    @Test
    public void should_return_list_with_multiple_elements_when_parse_given_flag_with_value_is_empty() {
        //given
        Map<String, String> keyValuePairs = parseByLexer("-l -p 8080 -d /usr/logs");

        //then
        assertEquals(3, keyValuePairs.size());
        assertEquals("", keyValuePairs.get("l"));
        assertEquals("8080", keyValuePairs.get("p"));
        assertEquals("/usr/logs", keyValuePairs.get("d"));
    }

    @Test
    public void should_return_list_when_parse_given_flag_value_with_empty_space_between() {
        //given
        Map<String, String> keyValuePairs = parseByLexer("    -l  true ");

        //then
        assertEquals(1, keyValuePairs.size());
        assertEquals("true", keyValuePairs.get("l"));
    }

    @Test
    public void should_return_list_when_parse_given_flag_value_with_empty_space_at_start_and_end() {
        //given
        Map<String, String> keyValuePairs = parseByLexer("    -l  true ");

        //then
        assertEquals(1, keyValuePairs.size());
        assertEquals("true", keyValuePairs.get("l"));
    }

    @Test(expected = InvalidInputException.class)
    public void should_throw_exception_when_parse_given_empty_string() {
        //given
        String source = " ";
        Lexer lexer = new Lexer();

        //when
        lexer.scan(source);
    }
}
