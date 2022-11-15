package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.exception.ExistStorageException;
import alex.jelia.empmanager.webapp.exception.NotExistStorageException;
import alex.jelia.empmanager.webapp.exception.StorageException;
import alex.jelia.empmanager.webapp.model.Resume;
import alex.jelia.empmanager.webapp.sql.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public DataBaseStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM resume");) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void update(Resume r) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("update resume set full_name = ? where uuid =?")) {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume r) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into resume(uuid,full_name) values (?,?)")) {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new ExistStorageException(e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume as r WHERE r.uuid = ?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume r = new Resume(uuid, rs.getString("full_name"));
            return r;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("delete from resume where uuid = ?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.wasNull()) {
                throw new NotExistStorageException(uuid);
            }
        } catch (SQLException e) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume order by uuid")) {
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            ArrayList<Resume> list = new ArrayList<>();
            while (rs.next()) {
                Resume r = new Resume(rs.getString("uuid").trim(),rs.getString("full_name"));
                list.add(r);
            }
            return list;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int size() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ")) {
            ps.executeQuery();
            int counter = 0;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                counter++;
            }
            return counter;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    //TODO test methods then delete main
    public static void main(String[] args) {
        DataBaseStorage start = new DataBaseStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "1234");
        Resume R1 = new Resume("12345", "Save in DB 1");
        Resume R2 = new Resume("123456", "Save in DB 2");

        System.out.println(start.getAllSorted());
        System.out.println(start.size());
    }

}
