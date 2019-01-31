package com.globant;

public class FactoryProducer {
    public static AbstractFactory getFactory (boolean Sql){
        if(Sql){
            return new SQLFactory();
        } else {
            return new NoSQLFactory();
        }
    }
}
