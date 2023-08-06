package com.lhc;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class SpringbootWebManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 生成JWT
     */
    @Test
    public void testGenJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "lhcJwt")    //签名算法
                .setClaims(claims)  //自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000))    //设置有效期1h
                .compact();
        System.out.println(jwt);
    }

    /**
     * 解析JWT
     */
    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("lhcJwt")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY5MTEyNzk0M30.9qdffeXxu3F27L_aL5g8GP0-bEuv5qdCtOPghoripHw")
                .getBody();
        System.out.println(claims);
    }

    /**
     * 自己测试用的
     */
    @Test
    public void myTest(){
        int[] a = new int[]{1, 2, 3};
        System.out.println(Arrays.toString(a));
    }

}
