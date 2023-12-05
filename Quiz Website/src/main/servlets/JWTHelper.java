package main.servlets;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class JWTHelper {

    private static String secret = "randomsecretkeyforjwt";

    public record UserData(String username, String role) {

    }

    public static void setBearerToken(HttpServletRequest request, UserData data) {
        long expirationMillis = System.currentTimeMillis() + 3600000*24; // 1 day
        Date expiration = new Date(expirationMillis);

        String jwt = Jwts.builder()
                .setSubject(null)
                .claim("username", data.username())
                .claim("role", data.role())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        request.getSession().setAttribute("Authorization", jwt);
    }

    public static UserData getDataFromToken(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody();

        return new UserData(
                claims.get("username", String.class),
                claims.get("role", String.class));
    }
}
