package Factories;

import Users.Recipient;

public class RecipientFactory {

    private final Recipient recipient;

    public RecipientFactory(String arr[]){
        recipient = new Recipient(Integer.parseInt(arr[1]), arr[2], arr[3], arr[4], Integer.parseInt(arr[5]), arr[6], arr[7], arr[8], arr[9]);
    }

    public Recipient getRecipient() {
        return recipient;
    }
}
