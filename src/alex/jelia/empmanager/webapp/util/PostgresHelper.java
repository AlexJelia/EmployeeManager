package alex.jelia.empmanager.webapp.util;

import alex.jelia.empmanager.webapp.exception.StorageException;
import alex.jelia.empmanager.webapp.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresHelper implements DBHelper{

    @Override
    public void doQuery(ConnectionFactory connectionFactory, String query) throws SQLException {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {

        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
