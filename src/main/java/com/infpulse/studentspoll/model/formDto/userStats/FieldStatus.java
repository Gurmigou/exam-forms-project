package com.infpulse.studentspoll.model.formDto.userStats;

public enum FieldStatus {
    CORRECT(200),
    WRONG(400),
    IGNORED(500);

    private final int value;

    FieldStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
