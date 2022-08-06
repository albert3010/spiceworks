package MovieBooking.entity;

import java.util.List;

public class Theater {
    int id;
    String name;
    String location;
    String description;
    List<Screen> screens;

    public Theater(int id, String name, String location, String description, List<Screen> screens) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.screens = screens;
    }
}
