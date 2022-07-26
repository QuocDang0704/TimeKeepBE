package com.example.timekeepv1.entity;

public enum EnumPosition {
    TRUONGPHONG(1),
    NHANVIEN(2),
    LAOCONG(3);
    private int value;

    EnumPosition(int value) {
        this.value = value;
    }

    public static String getEnum(int value) {
        if (value == 1) {
            return EnumPosition.TRUONGPHONG.name();
        } else if (value == 2) {
            return EnumPosition.NHANVIEN.name();
        } else if (value == 3) {
            return EnumPosition.LAOCONG.name();
        } else {
            return null;
        }
    }
}
