package com.thoughtworks.basic;

import com.thoughtworks.basic.exception.InvalidInputException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    Map<String, String> scan(String source) {
        if (isEmpty(source)) {
            throw new InvalidInputException();
        }

        return scanNonEmptyString(source);
    }

    private boolean isEmpty(String source) {
        return source.isEmpty() || source.trim().isEmpty();
    }

    private Map<String, String> scanNonEmptyString(String source) {
        List<String> pairs = breakupToPairs(source);

        return transformToMap(pairs);
    }

    private List<String> breakupToPairs(String source) {
        String PARSE_REGEX = "(?:\\-[a-zA-Z]\\s+[^\\-]\\S+)|(?:\\-[a-zA-Z]\\s+)";
        List<String> pairs = new ArrayList<>();

        Matcher matcher = Pattern.compile(PARSE_REGEX)
                .matcher(source);

        while (matcher.find()) {
            pairs.add(matcher.group().trim());
        }

        return pairs;
    }

    private Map<String, String> transformToMap(List<String> pairs) {
        Map<String, String> keyValuePairs = new LinkedHashMap<>();

        pairs.forEach(pair -> {
            String[] keyAndValue = splitToKeyAndValue(pair);
            keyValuePairs.put(pickKey(keyAndValue), pickValue(keyAndValue));
        });

        return keyValuePairs;
    }

    private String pickKey(String[] keyAndValue) {
        return keyAndValue[0].substring(1);
    }

    private String pickValue(String[] keyAndValue) {
        if (keyAndValue.length > 1) {
            return keyAndValue[1];
        }

        return "";
    }

    private String[] splitToKeyAndValue(String pair) {
        return pair.split("\\s+");
    }
}
