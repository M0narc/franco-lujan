package com.globant;

public class SQLFactory extends AbstractFactory{

    @Override
    public Connection getConnection(String connectionType) {
        if(connectionType.equalsIgnoreCase("MYSQL")) {
            return new MySQL();
        } else if (connectionType.equalsIgnoreCase("MYSQLLITE")){
            return new MySQLlite();
        }
        return null;
    }
}
