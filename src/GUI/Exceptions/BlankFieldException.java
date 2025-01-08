package GUI.Exceptions;

public class BlankFieldException extends InputValidationFailureException
{
    public BlankFieldException() {}

    public BlankFieldException(String message)
    { super(message); }
}