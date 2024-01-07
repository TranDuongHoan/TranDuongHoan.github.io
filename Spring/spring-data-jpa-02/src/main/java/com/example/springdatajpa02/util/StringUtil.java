package com.example.springdatajpa02.util;

import org.springframework.util.StringUtils;

public class StringUtil {

    public static String escapeWildCardCharacter(String str) {
        if (!StringUtils.hasText(str)) {
            return null;
        }
        return str.trim()
                .replace("%", "\\%")
                .replace("_", "\\_")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("{", "\\{")
                .replace("}", "\\}")
                ;
    }

}
