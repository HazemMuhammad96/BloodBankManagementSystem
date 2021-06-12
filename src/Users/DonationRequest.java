package Users;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.Calendar;

public class DonationRequest {
 long requestDate;
 long appointmentDate;
 int ID;
 String bloodType;
    public DonationRequest(int ID , String bloodType) {
        this.ID= ID;
        this.bloodType = bloodType;
        Calendar C=Calendar.getInstance();
        requestDate=C.getTimeInMillis();
       C.add(Calendar.WEEK_OF_MONTH,1);
       appointmentDate=C.getTimeInMillis();
    }

    public long getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(long requestDate) {
        this.requestDate = requestDate;
    }

    public long getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(long appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        String s = ID + "-" + bloodType + "-" + requestDate + "-" + appointmentDate;
        return s;
    }
}
