package com.thoughtworks.basic;

import com.thoughtworks.basic.exception.EmptyStringException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LexerTest {
    private List<ArgumentTO> parseByLexer(String source) {
        //given
        Lexer lexer = new Lexer();

        //when
        return lexer.scan(source);
    }

    @Test
    public void should_return_list_with_one_element_when_parse_given_one_flag_value() {
        List<ArgumentTO> argumentTOs = parseByLexer("-l true");

        //then
        assertEquals(1, argumentTOs.size());
        assertEquals("l", argumentTOs.get(0).getFlag());
        assertEquals("true", argumentTOs.get(0).getValue());
    }

    @Test
    public void should_return_list_with_multiple_elements_when_parse_given_multiple_flag_value() {
        //given
        List<ArgumentTO> argumentTOs = parseByLexer("-l true -p 8080 -d /usr/logs");

        //then
        assertEquals(3, argumentTOs.size());
        assertEquals("l", argumentTOs.get(0).getFlag());
        assertEquals("true", argumentTOs.get(0).getValue());
        assertEquals("p", argumentTOs.get(1).getFlag());
        assertEquals("8080", argumentTOs.get(1).getValue());
        assertEquals("d", argumentTOs.get(2).getFlag());
        assertEquals("/usr/logs", argumentTOs.get(2).getValue());
    }

    @Test
    public void should_return_list_with_multiple_elements_when_parse_given_flag_with_value_is_empty() {
        //given
        List<ArgumentTO> argumentTOs = parseByLexer("-l -p 8080 -d /usr/logs");

        //then
        assertEquals(3, argumentTOs.size());
        assertEquals("l", argumentTOs.get(0).getFlag());
        assertEquals("", argumentTOs.get(0).getValue());
        assertEquals("p", argumentTOs.get(1).getFlag());
        assertEquals("8080", argumentTOs.get(1).getValue());
        assertEquals("d", argumentTOs.get(2).getFlag());
        assertEquals("/usr/logs", argumentTOs.get(2).getValue());
    }

    @Test
    public void should_return_list_when_parse_given_flag_value_with_empty_space_between() {
        //given
        List<ArgumentTO> argumentTOs = parseByLexer("    -l  true ");

        //then
        assertEquals(1, argumentTOs.size());
        assertEquals("l", argumentTOs.get(0).getFlag());
        assertEquals("true", argumentTOs.get(0).getValue());
    }

    @Test
    public void should_return_list_when_parse_given_flag_value_with_empty_space_at_start_and_end() {
        //given
        List<ArgumentTO> argumentTOs = parseByLexer("    -l  true ");

        //then
        assertEquals(1, argumentTOs.size());
        assertEquals("l", argumentTOs.get(0).getFlag());
        assertEquals("true", argumentTOs.get(0).getValue());
    }

    @Test(expected = EmptyStringException.class)
    public void should_throw_exception_when_parse_given_empty_string() {
        //given
        String source = " ";
        Lexer lexer = new Lexer();

        //when
        lexer.scan(source);
    }
}
