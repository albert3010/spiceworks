package model;

import lombok.Getter;

@Getter
public class Input {
    int id;
    String name;
    Integer managerId;
    String address;

    public Input(int id, String name, Integer managerId, String address) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.address = address;
    }
}
