package Blood;

public class Blood {
    String type;
    int quantity;

    long receivedDate, expiredDate;

    public Blood(String type, int quantity, long receivedDate, long expiredDate) {
        this.type = type;
        this.quantity = quantity;
        this.receivedDate = receivedDate;
        this.expiredDate = expiredDate;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(long receivedDate) {
        this.receivedDate = receivedDate;
    }

    public long getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(long expiredDate) {
        this.expiredDate = expiredDate;
    }
}
