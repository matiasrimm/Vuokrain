package com.rim.vuokrain.exception;

public class DuplicateUsernameException extends Throwable {

	private static final long serialVersionUID = -3487620855643312548L;

	public DuplicateUsernameException(final String message) {
        super(message);
    }
}
