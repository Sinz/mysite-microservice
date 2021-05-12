package com.master.test.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @Classname ParseJwtTest
 * @Description TODO
 * @Date 2021/5/9 11:10
 * @Created by Zhao.J
 */
public class ParseJwtTest {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE2MjA1Mjk3NTR9.SCqfljS4f1FfI8G3cv8G8JjkV6-3JSFwMd95JJoxt5k";
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws(token).getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("IssuedAt:"+claims.getIssuedAt());
    }
}
