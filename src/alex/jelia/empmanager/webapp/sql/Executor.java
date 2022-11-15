package alex.jelia.empmanager.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Executor<T> {
    T exec(PreparedStatement st) throws SQLException;
}
