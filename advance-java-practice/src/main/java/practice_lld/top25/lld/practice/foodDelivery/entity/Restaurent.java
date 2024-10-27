package practice_lld.top25.lld.practice.foodDelivery.entity;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
public class Restaurent {
    String id;
    String name;
    String description;
    String location;
    Menu menu;

}
