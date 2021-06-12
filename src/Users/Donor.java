package Users;

import Blood.Disease;

import java.util.ArrayList;

public class Donor extends User {
    ArrayList<Disease> donorDisease = null;
    String dateOfLastDonation;

    public Donor() {}

    public Donor(int ID, String name, String mail, String password, int age, String gender, String bloodType, String date) {
        super(ID, name, mail, password, age, gender, bloodType);
        //this.donorDisease = donorDisease;
        dateOfLastDonation = date;
    }

    public void addDisease(String name) {
        donorDisease.add(new Disease(name));
    }

    @Override
    public String toString() {
        return "D-" + super.toString() + "-" + dateOfLastDonation;
    }


    public ArrayList<Disease> getDonorDisease() {
        return donorDisease;
    }

    public void setDonorDisease(ArrayList<Disease> donorDisease) {
        this.donorDisease = donorDisease;
    }

    public String getDateOfLastDonation() {
        return dateOfLastDonation;
    }

    public void setDateOfLastDonation(String dateOfLastDonation) {
        this.dateOfLastDonation = dateOfLastDonation;
    }
}
