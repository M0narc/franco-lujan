package com.globant;


public class MySQL implements Connection {

    @Override
    public void connect() {
        System.out.println("My SQL");
    }
}
