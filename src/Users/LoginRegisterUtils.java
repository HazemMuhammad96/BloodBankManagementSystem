package Users;

import java.util.List;
import java.util.regex.Pattern;

import Data.DataFiles;

public class LoginRegisterUtils {

    private DataFiles files = new DataFiles();
    private List<User> allUsers = files.getAllUsers();
    public static User loggedInUser;



    public LoginRegisterUtils() {
        files = new DataFiles();
    }


    // LOGIN
    public boolean validateUserLogin(String email, String password) {
        boolean isExist = false;
        for (User user : allUsers) {
            if (user.getMail().equalsIgnoreCase(email)) {
                if (user.getPassword().equals(password)) {
                    loggedInUser = user;
                    isExist = true;
                    break;
                }
            }
        }
        return isExist;
    }

    public void refreshLoggedInUser() {
        allUsers = files.getAllUsers();
        allUsers.forEach(user -> {
            if (user.getMail().equalsIgnoreCase(loggedInUser.getMail()))
                loggedInUser = user;
        });
    }



    // REGISTER
    public boolean validateUserRegistration(String email, String password) {
        boolean validEmail = isValidEmail(email);
        boolean uniqueEmail = isEmailUnique(email);
        boolean validPassword = isValidPassword(password);
        return validEmail && validPassword && uniqueEmail;
    }

    public boolean isEmailUnique(String email) {
        boolean isExist = false;
        for (User user : allUsers) {
            if (email.equalsIgnoreCase(user.getMail())) {
                isExist = true;
                break;
            }
        }
        return !isExist;
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&-]+)@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();

    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }



}
