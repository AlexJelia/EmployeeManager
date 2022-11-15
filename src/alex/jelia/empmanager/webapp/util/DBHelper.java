package alex.jelia.empmanager.webapp.util;

import alex.jelia.empmanager.webapp.sql.ConnectionFactory;

import java.sql.SQLException;

public interface DBHelper {
    public void doQuery(ConnectionFactory connectionFactory, String query) throws SQLException;
}
