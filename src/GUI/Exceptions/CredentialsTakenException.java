package GUI.Exceptions;

public class CredentialsTakenException extends InputValidationFailureException
{
    public CredentialsTakenException() {}

    public CredentialsTakenException(String message)
    { super(message); }
}