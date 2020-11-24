package com.test;

public class Foo {
    private int id;
    private String first;

    public Foo() {
    }

    public Foo(int i, String first) {
        this.id = i;
        this.first = first;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
}
