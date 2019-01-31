package com.globant;


public class NoSQL implements Connection {

    @Override
    public void connect() {
        System.out.println("No SQL");
    }
}