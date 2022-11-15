package alex.jelia.empmanager.webapp.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper {
    private final ConnectionFactory connectionFactory;

    public DBHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void doExecute(String query) {
        doExecute(query, PreparedStatement::execute);
    }

    public <T> T doExecute(String query, Executor<T> executor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            return executor.exec(ps);
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }
}
