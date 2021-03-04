package com.extension2;

public enum OpCode {

    CREATED("created"), FILLED("filled"), CANCELLED("cancelled");

    private String op;

    OpCode(String op) {
        this.op = op;
    }
}
