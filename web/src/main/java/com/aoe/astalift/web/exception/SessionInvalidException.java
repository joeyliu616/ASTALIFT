package com.aoe.astalift.web.exception;

import java.io.Serializable;

/**
 * Created by joey on 16-3-21.
 */
public class SessionInvalidException extends Exception implements Serializable{

    public SessionInvalidException(String message) {
        super(message);
    }

    public SessionInvalidException() {
    }
}
