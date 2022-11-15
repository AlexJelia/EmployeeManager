package alex.jelia.empmanager.webapp.sql;

import alex.jelia.empmanager.webapp.exception.ExistStorageException;
import alex.jelia.empmanager.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {

//            http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}