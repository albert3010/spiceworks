package practice_lld.top25.lld.practice.foodDelivery.entity.user;

public class DeliveryPartner extends User {
    boolean isAvailable;

    public DeliveryPartner(String id, String name, String email) {
        super(id, name, email);
        this.isAvailable = true;
    }
}
