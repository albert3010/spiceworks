package MovieBooking.entity;

import java.util.List;

public class City {
    int id;
    String name;
    List<Theater> theaters;

    public City(int id, String name, List<Theater> theaters) {
        this.id = id;
        this.name = name;
        this.theaters = theaters;
    }
    void addTheaters(Theater theaters){
        this.theaters.add(theaters);
    }
}
