package com.lys.usermanager.service;

/**
 * @description:
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-11-12 10:39
 **/
public class AAA2 {
    public static void main(String[] args) {
        int i = 1, j = 10;
        do {
            if (i > j) continue;
            j--;

        } while (++i < 6);
        System.out.println(i);
        System.out.println(j);

        float[] f1,f2;
        f1=new float[10];
        f2=f1;
        System.out.println(f2[0]);

        System.out.println("------------");
        System.out.println(16>>2);
        System.out.println(16/2^2);
        System.out.println(16>>>2);

        if(true==true){

        }
    }

    protected void setA(float f) {

    }
}

class AAs extends AAA2 {

    @Override
    public final void setA(float f) {

    }
}
