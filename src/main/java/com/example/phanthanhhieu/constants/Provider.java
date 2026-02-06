package com.example.phanthanhhieu.constants;

public enum Provider {
    LOCAL("Local"),
    GOOGLE("Google");

    public final String value;

    Provider(String value) {
        this.value = value;
    }
}