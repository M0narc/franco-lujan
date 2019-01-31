package com.globant;

public class NoSQLFactory extends AbstractFactory {

    @Override
    public Connection getConnection(String connectionType) {
        if (connectionType.equalsIgnoreCase("MYSQL")) {
            return new NoSQL();
        } else if (connectionType.equalsIgnoreCase("MYSQLLITE")) {
            return new NoSQL();
        }
        return null;
    }
}