package practice_lld.top25.lld.splitwise.split;

import practice_lld.top25.lld.splitwise.entity.User;

import java.util.Map;

public interface Split {

    Map<User, Double> split(Double amount, Map<User, Double> usersPaid) throws Exception;


    default boolean validate(){
        return true;
    }

}
