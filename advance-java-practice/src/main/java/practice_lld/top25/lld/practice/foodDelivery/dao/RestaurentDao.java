package practice_lld.top25.lld.practice.foodDelivery.dao;

import practice_lld.top25.lld.practice.foodDelivery.entity.Restaurent;

import java.util.Map;

public class RestaurentDao {
    Map<String, Restaurent> restaurentMap;

    public Restaurent getRestaurent(String id) {
        return restaurentMap.get(id);
    }
    void addRestaurent(Restaurent restaurent){
        restaurentMap.put(restaurent.getId(), restaurent);
    }
}
