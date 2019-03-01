package com.bootcamp.shoppingcart.constants;

public enum ExceptionMessages {

    UnauthorizedException("Cannot login"),

    BAD_REQUEST_CATEGORY_NAME_MUST_BE_UNIQUE("There is already a category with that name."),
    BAD_REQUEST_PERSON_USERNAME_MUST_BE_UNIQUE("There is already a person with that userName."),

    NOT_FOUND_CATEGORY("Category does not exist"),
    NOT_FOUND_USER("User does not exist"),
    NOT_FOUND_ITEM("Item does not exist");

    private String string;

    ExceptionMessages(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
