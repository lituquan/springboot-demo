package com.ltq.undertow.pattern.template;

public class Test {
    public static void main(String[] args) {
        Standard std = new Primary();
        std.stand();

        std = new Middle();
        std.stand();
    }
}