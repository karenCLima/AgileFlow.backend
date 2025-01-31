package com.br.AgileFlow.backend.core.util;

import java.util.Base64;

public abstract class Base64Converter {

    public static String encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static String decode(String input) {
        byte[] decoded = Base64.getDecoder().decode(input);
        return new String(decoded);
    }
}
