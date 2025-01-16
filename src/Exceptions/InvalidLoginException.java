package Exceptions;

public class InvalidLoginException extends InputValidationFailureException
{
    public InvalidLoginException() {}

    public InvalidLoginException(String message)
    { super(message); }
}