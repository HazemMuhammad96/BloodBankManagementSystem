package Blood;

public class Blood {
 String type;
 int quantity;
 String receivedDate,expiredDate;

    public Blood(String type, int quantity, String receivedDate, String expiredDate) {
        this.type = type;
        this.quantity = quantity;
        this.receivedDate = receivedDate;
        this.expiredDate = expiredDate;
    }

}
