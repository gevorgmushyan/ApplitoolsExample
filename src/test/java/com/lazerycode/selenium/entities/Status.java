package com.lazerycode.selenium.entities;

public enum Status {

    TODO("TODO"),
    DONE("DONE");

    private String state;

    Status(String state) {
        this.state = state;
    }
}
