package com.westay.live.modules.admin.enums;

public enum UserVipEnum {

    YES(1),
    NO(0);

    private int value;

    UserVipEnum(int value) {this.value = value;}

    public int value() {return this.value;}
}
