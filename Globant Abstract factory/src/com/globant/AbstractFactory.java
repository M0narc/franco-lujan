package com.globant;

public abstract class AbstractFactory {
    abstract Connection getConnection(String connectionType);
}