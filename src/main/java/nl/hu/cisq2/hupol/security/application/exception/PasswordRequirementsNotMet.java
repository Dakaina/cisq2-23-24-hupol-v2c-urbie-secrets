package nl.hu.cisq2.hupol.security.application.exception;

public class PasswordRequirementsNotMet extends RuntimeException {
    public PasswordRequirementsNotMet(){
        super("Password should have 12 characters existing out of at least 2 uppercase letter, 2 lowercase letters, 2 numbers," +
                " 2 special characters (~`!@#$%^&*()_-+=[]{}|:;<>,./?')");
    }
}

