package com.lazerycode.selenium.entities;

public enum Priority {

    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private String type;

    Priority(String type) {
        this.type = type;
    }
}
