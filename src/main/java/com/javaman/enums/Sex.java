package com.javaman.enums;

/**
 * @author chx
 * @description com.javaman
 * @date 2018/8/29
 */
public enum Sex {

    MAN(1,"男"),WOMAN(0,"女");

    private int code;
    private String sex;

   private Sex(int code,String sex){
        this.code = code;
        this.sex = sex;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
