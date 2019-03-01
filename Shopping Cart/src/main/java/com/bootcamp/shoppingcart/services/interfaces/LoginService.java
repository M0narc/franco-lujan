package com.bootcamp.shoppingcart.services.interfaces;

public interface LoginService {

    boolean login (String username, String pass);

    boolean logout (String username, String pass);

    boolean validate (String username, String pass);
}
