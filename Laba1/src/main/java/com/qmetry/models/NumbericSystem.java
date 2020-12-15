package com.qmetry.models;

public enum NumbericSystem {
    binary(new char[] {'0', '1'}),
    hexadecimal(new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}),
    decimal(new char[]{'0','1','2','3','4','5','6','7','8','9'});

    public final char[] digitsAlphabet;

    NumbericSystem(char[] digitsAlphabet) {
        this.digitsAlphabet = digitsAlphabet;
    }
}
