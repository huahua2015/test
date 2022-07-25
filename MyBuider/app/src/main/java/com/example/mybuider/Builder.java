package com.example.mybuider;

public abstract  class Builder {
    abstract  Builder buildBoard(String board);
    abstract  Builder buildDisplay(String display);
    abstract  Builder buildOS();
    abstract Computer create();
}
