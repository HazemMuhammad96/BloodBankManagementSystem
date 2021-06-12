package Users;

public class Recipient extends User {
    String hospital;
    String doctorOfCase;

    public Recipient() {}

    public Recipient(int ID, String name, String mail, String password, int age, String gender, String bloodType, String hospital, String doctorOfCase) {
        super(ID, name, mail, password, age, gender, bloodType);
        this.hospital = hospital;
        this.doctorOfCase = doctorOfCase;
    }

    @Override
    public String toString() {
        return "R-" + super.toString() + "-" + hospital + "-" + doctorOfCase;
    }


    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDoctorOfCase() {
        return doctorOfCase;
    }

    public void setDoctorOfCase(String doctorOfCase) {
        this.doctorOfCase = doctorOfCase;
    }
}
