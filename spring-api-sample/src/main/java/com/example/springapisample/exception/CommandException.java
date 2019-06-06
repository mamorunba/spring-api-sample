package com.example.springapisample.exception;

/**
 * コマンド実行エラーのException
 */
public class CommandException extends ApplicationException {

    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
    public CommandException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
