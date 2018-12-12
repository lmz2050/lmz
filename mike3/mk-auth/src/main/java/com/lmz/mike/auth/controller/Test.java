package com.lmz.mike.auth.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 2 * @Author: Mike Lee
 * 3 * @Date: 2018/12/2 23:03
 * 4
 */
public class Test {

    public static void main(String[] args) {


        String aa = new BCryptPasswordEncoder().encode("123456");
//$2a$10$LIFXUA9TPjupXryTqZHHYOSJ7rz9EuHJ7t3geK0eE8g320pCFRQT2
//$2a$10$Pfu1VsYV1g0h4fw/OW3sp.AP8/AsxwBcvZkqGOr3a95AdJwGKAsIu
        System.out.println(aa);

    }
}
