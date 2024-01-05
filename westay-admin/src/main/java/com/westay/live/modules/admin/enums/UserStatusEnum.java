package com.westay.live.modules.admin.enums;

public enum UserStatusEnum {
    YES(1),
    NO(0);

    private int value;

    UserStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
