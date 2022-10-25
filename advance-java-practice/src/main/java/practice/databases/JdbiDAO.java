package practice.databases;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * Created by omprakash.yadav on 06/03/16.
 */
public interface JdbiDAO {
    String tableName = "city";

    @SqlUpdate("INSERT INTO " + tableName +
            " ( state_id, city_id) " +
            " VALUES( :st, :ct)")
    void insertRow(@Bind("st") int state,  @Bind("ct") int city);

}
