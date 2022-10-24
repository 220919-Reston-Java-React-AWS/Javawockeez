package com.revature.exceptions;

public class QueryException extends Exception {
    public QueryException(){
        super();
    }
    public QueryException(String message){
        super(message);
    }
}
