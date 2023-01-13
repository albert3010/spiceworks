package practice_lld_2023.machine_coding.zoomcar.entity;

import practice_lld_2023.machine_coding.zoomcar.constants.Constant;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class User {
     int id;
     String name;
     boolean isAdmin;
     Date createdAt;
     Date updatedAt;

     User(String name, boolean isAdmin){
          id = Constant.getUserId();
          this.name = name;
          this.isAdmin = isAdmin;
          this.createdAt = Calendar.getInstance().getTime();
          this.updatedAt = Calendar.getInstance().getTime();
     }

}
