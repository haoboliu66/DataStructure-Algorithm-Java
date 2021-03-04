package com.basic;

public enum OpCode {

    CREATED("created"), FILLED("filled"), CANCELLED("cancelled");

    private String op;

    OpCode(String op) {
        this.op = op;
    }
}
