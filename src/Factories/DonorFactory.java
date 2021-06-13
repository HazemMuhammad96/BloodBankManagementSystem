package Factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Users.Donor;

public class DonorFactory {

    private final Donor donor;

    public DonorFactory(String arr[]) {
        donor = new Donor(Integer.parseInt(arr[1]), arr[2], arr[3], arr[4], Integer.parseInt(arr[5]), arr[6], arr[7], Long.parseLong(arr[8]));

        if (arr.length == 10) {
            List<String> s = Arrays.asList(arr[9].split("^"));
            donor.setDonorDisease(s);
        }
    }

    public Donor getDonor() {
        return donor;
    }
}
