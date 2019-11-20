package databases;

import org.junit.Test;
import org.skife.jdbi.v2.DBI;

/**
 * Created by omprakash.yadav on 06/03/16.
 */
public class JdbiDriver {

    @Test
    public void insertIntoTable() {
        JdbiDAO dao = (new DBI("jdbc:mysql://127.0.0.1:3306/india", "root", "")).onDemand(JdbiDAO.class);
        dao.insertRow(22, 111);


    }
}
