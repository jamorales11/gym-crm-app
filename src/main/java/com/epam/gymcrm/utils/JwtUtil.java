package com.epam.gymcrm.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.AeadAlgorithm;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;

public class JwtUtil {

    private static final SecretKey key = Jwts.SIG.HS256.key().build();
    private static final String SECRET = key.toString();

    //private static final String SECRET = "your-secret-key";


    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
        /*
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .encryptWith(key,Jwts.KEY.DIRECT,Jwts.ENC.A128CBC_HS256)
                .compact();

         */
    }

    public static String extractUsername(String token) {
        String username = "";

        try {
            username = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload().getSubject();

            /*
            return Jwts.parser()
                    .decryptWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload().getSubject();

             */

        }
        catch (JwtException ex){
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }

        return username;


    }
}
