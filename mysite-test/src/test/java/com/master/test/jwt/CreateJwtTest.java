package com.master.test.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

/**
 * @Classname CreateJwtTest
 * @Description 创建Jwt
 *  test1 : 基本应用
 *  test2 : 设置过期时间
 *  test3 : 自定义claims
 * @Date 2021/5/9 11:13
 * @Created by Zhao.J
 */
public class CreateJwtTest {

    @Test
    public void Test1(){
        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast");
        System.out.println( builder.compact() );
    }

    @Test
    public void Test2(){

        //为了方便测试，我们将过期时间设置为1分钟
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 1000*60;//过期时间为1分钟

        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast")
                .setExpiration(new Date(exp));; // 设置过期时间
        System.out.println( builder.compact() );
    }

    @Test
    public void Test3(){
        //为了方便测试，我们将过期时间设置为1分钟
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 1000*60;//过期时间为1分钟

        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast")
                .setExpiration(new Date(exp)) // 设置过期时间
                .claim("roles","admin") .claim("logo","logo.png");
        System.out.println( builder.compact() );
    }
}
