package com.lys.usermanager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-11-07 08:43
 **/
public class AAA implements Runnable {
    static String str =new String("good");
    static char[] ch={'a','b','c'};

    public static void main(String[] args) {
        /*String s = "abc";
        s.substring(1);
        s.replace("bc", "xyz");
        System.out.println(s);
        String value = new String("abc");
        System.out.println(s == value);*/

        AAA aaa = new AAA();
        aaa.run();
        aaa.change(str,ch);
        System.out.println(str);
        System.out.println(ch);

        String[] s=new String[10];
        System.out.println(s.length);
        for (int i = 0; i <s.length ; i++) {
            System.out.println(s[i]);
        }

        System.out.println(new BCryptPasswordEncoder().encode("123456"));


        Map<String,Integer> map=new HashMap();
        map.put("zhangsan",21);
        //。。。把数据都填进去
        for (String s1 : map.keySet()) {
            System.out.println(s1+","+map.get(s1));
        }

        /*Jwt jwt = JwtHelper.decodeAndVerify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidXNlcm1hbmFnZXIiLCJyZXNvdXJzZTIiLCJyZXNvdXJzZTEiXSwiZXhwIjoxNTczNzM2MjExLCJ1c2VyX25hbWUiOiJseXMxMTEiLCJqdGkiOiI5Mzk2NmI2Zi0zNzZhLTQyZDQtODY5ZS1kMzMyNGU4NTMzYjMiLCJjbGllbnRfaWQiOiJjbGllbnRhcHAiLCJzY29wZSI6WyJTQ09QRV9XUklURSIsInMxIiwiczIiXX0.6uUxKfzZZFI6a0Ew_yRzTJhNYpVcQ3qcucZyj8XQ6dI",
                "111");*/






        int i=12;
        System.out.println(i+=i-=i*=i);


    }

    public void change(String ssq, char[] ch){
        ssq="aaaaaaaa";
        ch[0]='g';
    }


    public void run() {

    }
}

