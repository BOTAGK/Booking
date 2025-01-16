package Exceptions;

public class InvalidSyntaxException extends InputValidationFailureException
{
    public InvalidSyntaxException() {}

    public InvalidSyntaxException(String message)
    { super(message); }
}