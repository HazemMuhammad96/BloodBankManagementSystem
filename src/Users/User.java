package Users;

import java.util.Comparator;
import java.util.regex.Pattern;

public abstract class User {
    private int ID;
    private int age;
    private String name;
    private String mail;
    private String password;
    private String gender;
    private String bloodType;


    public User() {}

    public User(int ID, String name, String mail, String password, int age, String gender, String bloodType) {
        this.ID = ID;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.bloodType = bloodType;
    }

    @Override
    public String toString() {
        String s = ID + "%" + name + "%" + mail + "%" + password + "%" + age + "%" + gender + "%" + bloodType;
        return s;
    }


    public static final Comparator<User> BY_ID = Comparator.comparingInt(User::getID);


    @Override
    public boolean equals(Object obj) {

        if(obj instanceof User) {
            if (((User) obj).getID() == getID())
                return true;
            else return false;
        }
        return false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
