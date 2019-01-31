package com.globant;

public class Main {

    public static void main(String[] args) {
        AbstractFactory mysql = FactoryProducer.getFactory(true);

        Connection connection = mysql.getConnection("MYSQL");
        connection.connect();
        Connection connection1= mysql.getConnection("MYSQLLITE");
        connection1.connect();

        AbstractFactory mysql2 = FactoryProducer.getFactory(false);

        Connection connection2 = mysql2.getConnection("MySQL");
        connection2.connect();
        Connection connection3 = mysql2.getConnection("MYSQLLITE");
        connection3.connect();

    }
}
