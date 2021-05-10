package com.nure.yehor;

public class Range {
    public String toString(int from, int to) {
        StringBuilder sb = new StringBuilder();
        for (int i = from; i < to; i++) {
            sb.append(i).append(" ");
        }
        sb.append(to);
        return sb.toString();
    }
}
