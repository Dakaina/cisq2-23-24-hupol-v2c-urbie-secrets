package nl.hu.cisq2.hupol.security.util;

public class CredentialChecker {
    static public boolean satisfiesPasswordRequirements(String password){
        String allowedSpecialCharacters = "~`!@#$%^&*()_-+=[]{}|:;<>,./?'";
        int uppercase = 0;
        int lowercase = 0;
        int digits = 0;
        int specialCharacters = 0;

        for (int i = 0; i < password.length(); i++){
            char character = password.charAt(i);

            if (character >= 'A' && character <= 'Z'){
                uppercase++;
            }
            else if (character >= 'a' && character <= 'z'){
                lowercase++;
            }
            else if (Character.isDigit(character)){
                digits++;
            }
            else if (allowedSpecialCharacters.contains(Character.toString(character))){
                specialCharacters++;
            }
            else {
                return false;
            }
        }

        return password.length() > 12 && uppercase > 1 && lowercase > 1 && digits > 1 && specialCharacters > 1;
    }
}
