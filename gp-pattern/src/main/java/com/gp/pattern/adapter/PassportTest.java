package com.gp.pattern.adapter;

import com.gp.pattern.adapter.service.PassportForThirdAdapter;

public class PassportTest {

    public static void main(String[] args) {
        PassportForThirdAdapter passportForThirdAdapter = new PassportForThirdAdapter();
        System.out.println(passportForThirdAdapter.loginString("abc","12121"));

        System.out.println(passportForThirdAdapter.registered("abc","12121"));

        System.out.println(passportForThirdAdapter.loginForQQ("abc"));

        System.out.println(passportForThirdAdapter.loginForWetchat("abc"));

    }

}
