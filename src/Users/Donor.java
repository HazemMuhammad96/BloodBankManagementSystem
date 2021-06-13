package Users;

import Blood.Disease;

import java.util.ArrayList;
import java.util.List;

public class Donor extends User {
    List<String> donorDisease = null;
    long dateOfLastDonation;

    public Donor() {}

    public Donor(int ID, String name, String mail, String password, int age, String gender, String bloodType,
                 long date) {
        super(ID, name, mail, password, age, gender, bloodType);
        dateOfLastDonation = date;
    }

    public void addDisease(String name) {
        donorDisease.add(name);
    }

    @Override
    public String toString() {
        String s = "D%" + super.toString() + "%" + dateOfLastDonation + "%";
        for (int i = 0; i < donorDisease.size(); i++) {
            s += donorDisease.get(i);
            if (i != donorDisease.size() - 1) s += "^";
        }
        return s;
    }



    public List<String> getDonorDisease() {
        return donorDisease;
    }

    public void setDonorDisease(List<String> donorDisease) {
        this.donorDisease = donorDisease;
    }

    public long getDateOfLastDonation() {
        return dateOfLastDonation;
    }

    public void setDateOfLastDonation(long dateOfLastDonation) {
        this.dateOfLastDonation = dateOfLastDonation;
    }
}
