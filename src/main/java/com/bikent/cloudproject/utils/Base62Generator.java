package com.bikent.cloudproject.utils;

import lombok.NoArgsConstructor;

import java.util.Random;

public class Base62Generator {
    private Base62Generator() {}
    private static final String CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    public static String nextBase62Code(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(CHARSET.charAt(random.nextInt(62)));
        }
        return builder.toString();
    }
    
    
}
