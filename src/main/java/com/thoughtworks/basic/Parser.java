package com.thoughtworks.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private String source;

    public Parser(String source) {
        this.source = source;
    }

    List<String> parse() {
        if (source.isEmpty() || source.trim().isEmpty()) {
            throw new EmptyStringException();
        }

        return parseNonEmptyString();
    }

    private List<String> parseNonEmptyString() {
        String PARSE_REGEX = "\\-[a-zA-Z]\\s+\\S+";
        List<String> result = new ArrayList<>();

        Matcher matcher = Pattern.compile(PARSE_REGEX)
                .matcher(source);

        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }
}
