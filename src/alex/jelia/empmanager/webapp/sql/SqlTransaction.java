package alex.jelia.empmanager.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlTransaction <T>{
    T exec(Connection conn) throws SQLException;
}
