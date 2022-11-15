package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.exception.NotExistStorageException;
import alex.jelia.empmanager.webapp.model.Resume;
import alex.jelia.empmanager.webapp.sql.DBHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseStorage implements Storage {
    public final DBHelper helper;

    public DataBaseStorage(String dbUrl, String dbUser, String dbPassword) {
        helper = new DBHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        helper.doExecute("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        helper.doExecute("update resume set full_name = ? where uuid =?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        helper.doExecute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return helper.doExecute("SELECT * FROM resume r WHERE r.uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void delete(String uuid) {
        helper.doExecute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return helper.doExecute("SELECT * FROM resume as r ORDER BY full_name,uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return helper.doExecute("SELECT count(*) FROM resume", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}
