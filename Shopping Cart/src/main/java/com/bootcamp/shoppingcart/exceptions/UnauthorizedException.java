package com.bootcamp.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(){
        super();
    }

    public UnauthorizedException(String message, Throwable cause){
        super(message, cause);
    }

    public UnauthorizedException(String message){
        super(message);
    }

    public UnauthorizedException(Throwable cause){
        super(cause);
    }


}
