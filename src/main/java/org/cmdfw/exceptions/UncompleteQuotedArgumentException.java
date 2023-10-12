package org.cmdfw.exceptions;

public class UncompleteQuotedArgumentException extends Exception {
    private final String argument;

    public UncompleteQuotedArgumentException(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String getMessage() {
        return String.format("Argument not quoted properly: %s", this.argument);
    }
}
