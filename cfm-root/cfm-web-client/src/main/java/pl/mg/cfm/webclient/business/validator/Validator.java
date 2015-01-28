package pl.mg.cfm.webclient.business.validator;

public class Validator {

    public static boolean validateId(String id) {
        // TODO implement!
        return true;
    }

    public static boolean validatePassword(String password) {
        if (password == null || password.length() < 4) {
            return false;
        } else
            return true;
    }

    public static boolean validateFirstName(String firstName) {
        if (firstName == null || firstName.length() < 2) {
            return false;
        } else
            return true;
    }

    public static boolean validateLastName(String lastName) {
        if (lastName == null || lastName.length() < 2) {
            return false;
        } else
            return true;
    }

}
