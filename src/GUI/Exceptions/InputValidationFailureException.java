package GUI.Exceptions;

public class InputValidationFailureException extends Exception
{
    public InputValidationFailureException() {}

    public InputValidationFailureException(String message)
    { super(message); }
}