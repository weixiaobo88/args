package com.thoughtworks.basic;

import com.thoughtworks.basic.exception.InvalidInputCommandException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    List<ArgumentTO> scan(String source) {
        if (isEmpty(source)) {
            throw new InvalidInputCommandException();
        }

        return scanNonEmptyString(source);
    }

    private boolean isEmpty(String source) {
        return source.isEmpty() || source.trim().isEmpty();
    }

    private List<ArgumentTO> scanNonEmptyString(String source) {
        List<String> pairs = breakupToPairs(source);
        return transformToArgumentTOs(pairs);
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

    private List<ArgumentTO> transformToArgumentTOs(List<String> pairs) {
        List<ArgumentTO> argumentTOs = new ArrayList<>();

        pairs.forEach(pair -> argumentTOs.add(pickArgumentTO(pair)));

        return argumentTOs;
    }

    private ArgumentTO pickArgumentTO(String pair) {
        String[] split = pair.split("\\s+");
        String flag = split[0].substring(1);

        if (split.length > 1) {
            String value = split[1];

            return new ArgumentTO(flag, value);
        }

        String defaultValue = "";
        return new ArgumentTO(flag, defaultValue);
    }
}
