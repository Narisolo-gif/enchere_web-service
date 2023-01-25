package com.enchere.utils;

import java.security.SecureRandom;

public class TokenGenerator {
    protected static SecureRandom rand = new SecureRandom();

    public synchronized String generateToken() {
        long longToken = Math.abs( rand.nextLong() );
        String rand = Long.toString( longToken, 16 );
        return ( rand );
    }
}
