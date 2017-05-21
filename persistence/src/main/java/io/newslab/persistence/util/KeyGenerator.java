package io.newslab.persistence.util;


import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {

    private final static java.util.Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    private final static SecureRandom numberGenerator = new SecureRandom();

    public static String generateKey() {
        byte[] randomBytes = new byte[8];
        numberGenerator.nextBytes(randomBytes);

        return new String(encoder.encode(randomBytes));
    }
}
