package sample;

import Factories.DonorFactory;
import Factories.RecipientFactory;
import Users.Donor;
import Users.Recipient;
import Users.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class DataFiles {

    Path usersPath = Paths.get("usersInfo.txt");

    public DataFiles() {
        createFile();
    }

    public void createFile() {
        try {
            if (Files.exists(usersPath)) {
                System.out.println("File already exists");
            } else {
                Path donePath = Files.createFile(usersPath);
                System.out.println("File Created Successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
        Insertions
     */
    public void insertUser(User user) {

        try {
            Files.write(usersPath, (user.toString() + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        Retrievals
     */
    public List<User> getAllUsers() {
        List<String> s = null;
        try {
            s = Files.readAllLines(usersPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<User> users = s.stream().map(userString -> {
            String arr[] = userString.split("-");
            return checkUser(arr);
        }).collect(Collectors.toList());


        return users;
    }

    private User checkUser(String arr[]) {
        if (arr[0].equalsIgnoreCase("D"))
            return new DonorFactory(arr).getDonor();
        else return new RecipientFactory(arr).getRecipient();
    }

    public User getUser(String email) {

        final User[] user = {null};

        try {
            Files.lines(usersPath).forEach(Line -> {
                        String arr[] = Line.split("-");

                        if (arr[3].equalsIgnoreCase(email)) {
                            user[0] = checkUser(arr);
                            return;
                        }

                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user[0];
    }


    /*
        Modifications
     */

    public void updateUser(User updatedUser) {
        List<User> users = getAllUsers();
        users.remove(updatedUser);
        User newUser = updatedUser;
        users.add(newUser);
        users.sort(User.BY_ID);
        List<String> usersStrings = users.stream().map(user -> user.toString()).collect(Collectors.toList());
        try {
            Files.write(usersPath, usersStrings);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*
        Deletion
     */

    public void deleteUser(User deletedUser){
        List<User> users = getAllUsers();
        users.remove(deletedUser);
        users.sort(User.BY_ID);
        List<String> usersStrings = users.stream().map(user -> user.toString()).collect(Collectors.toList());
        try {
            Files.write(usersPath, usersStrings);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}