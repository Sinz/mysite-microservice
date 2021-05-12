package com.master.test.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Classname CreateJwtTest
 * @Description
 *  setIssuedAt用于设置签发时间
 *  signWith用于设置签名秘钥
 *
 * @Date 2021/5/9 11:06
 * @Created by Zhao.J
 */
public class CreateJwtTest {
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast");
        System.out.println( builder.compact() );

    }
}
