package Data;

import Blood.Blood;
import Factories.DonorFactory;
import Factories.RecipientFactory;
import Users.DonationRequest;
import Users.Donor;
import Users.Recipient;
import Users.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;


public class DataFiles {

    Path usersPath = Paths.get("usersInfo.txt");
    Path donationsPath = Paths.get("donationRequestInfo.txt");
    Path recipientPath = Paths.get("recipientInfo.txt");
    Path indexPath = Paths.get("path.txt");

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
            if (Files.exists(donationsPath)) {
                System.out.println("File already exists");
            } else {
                Path donePath = Files.createFile(donationsPath);
                System.out.println("File Created Successfully!");
            }
            if (Files.exists(recipientPath)) {
                System.out.println("File already exists");
            } else {
                Path donePath = Files.createFile(recipientPath);
                System.out.println("File Created Successfully!");
            }
            if (Files.exists(indexPath)) {
                System.out.println("File already exists");
            } else {
                Path donePath = Files.createFile(indexPath);
                Files.write(donePath, "1".getBytes());
                System.out.println("File Created Successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Users

    /*
        Insertions
     */
    public void insertUser(User user) {

        try {
//            int id = Files.lines(usersPath).collect(Collectors.toList()).size() + 1;
            int id = Integer.parseInt(Files.lines(indexPath).collect(Collectors.toList()).get(0));
            user.setID(id);
            Files.write(indexPath, (id + 1 + "").getBytes());
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
            String arr[] = userString.split("%");
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
                        String arr[] = Line.split("%");

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

    public void deleteUser(User deletedUser) {
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

    // Donations
    public void insertDonation(DonationRequest request) {
        try {
            Files.write(donationsPath, (request.toString() + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public DonationRequest getDonation(int ID) {
        final DonationRequest[] request = new DonationRequest[1];
        try {
            Files.lines(donationsPath).forEach(Line -> {

                        String arr[] = Line.split("%");

                        if (ID == Integer.parseInt(arr[0])) {
                            request[0] = new DonationRequest(ID, arr[1]);
                            request[0].setRequestDate(Long.parseLong(arr[2]));
                            request[0].setAppointmentDate(Long.parseLong(arr[3]));
                            return;
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request[0];
    }

    public List<Blood> getBloodData() {
        List<Blood> bloodList = new ArrayList<Blood>();

        try {
            List<DonationRequest> donations = Files.readAllLines(donationsPath).stream().map(userString -> {
                String arr[] = userString.split("%");
                DonationRequest request = new DonationRequest(Integer.parseInt(arr[0]), arr[1]);
                request.setRequestDate(Long.parseLong(arr[2]));
                request.setAppointmentDate(Long.parseLong(arr[3]));
                return request;
            }).collect(Collectors.toList());

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Map<String, List<DonationRequest>> collected = donations.stream()
                    .collect(groupingBy(DonationRequest::getBloodType));

            collected.forEach((key, value) -> {
                Map<String, List<DonationRequest>> collect = value.stream().collect(groupingBy(DonationRequest::getFormattedDate));
                collect.forEach((key2, value2) -> {
                    try {
                        bloodList.add(new Blood(key, value2.size(), formatter.parse(key2).getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        return bloodList;
    }

    //Blood Data
    public void insertRecipientRequest(int id, Blood blood, long requestDate) {
        try {
            Files.write(recipientPath,
                    (id + "%" + blood.getType() + "%" + blood.getQuantity() + "%" + requestDate + "\n").getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}