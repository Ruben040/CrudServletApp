package ru.ruben.crud.DAO;

import ru.ruben.crud.model.Developer;
import ru.ruben.crud.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoImpl implements DeveloperDao {

    private final ProgrammingLanguageDAO programmingLanguageDAO = ProgrammingLanguageDAOImpl.getInstance();

    private static class SingletonDao{
        private static final DeveloperDaoImpl INIT = new DeveloperDaoImpl();
    }

    public static DeveloperDaoImpl getInstance(){
        return SingletonDao.INIT;
    }

    @Override
    public Developer findById(String id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM developers WHERE id = ?";
        int id_dev = 0, age = 0;
        String firstName = "", lastName = "";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            id_dev = resultSet.getInt("id");
            firstName = resultSet.getString("firstName");
            lastName = resultSet.getString("lastName");
            age = resultSet.getInt("age");
        }
        statement.close();
        connection.close();
        return new Developer(id_dev, firstName, lastName, age);
    }

    @Override
    public List<Developer> findAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM developers";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id_dev = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            int age = resultSet.getInt("age");
            Developer developer = new Developer(id_dev, firstName, lastName, age);
            developers.add(developer);
        }
        statement.close();
        connection.close();
        return developers;
    }

    @Override
    public void save(Developer developer) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO developers (firstName, lastName, age) VALUES(?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, developer.getFirstName());
        statement.setString(2, developer.getLastName());
        statement.setInt(3, developer.getAge());
        statement.executeUpdate();
    }

    @Override
    public void update(Developer developer) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE developers SET firstName = ?, lastName = ?, age = ? WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, developer.getFirstName());
        statement.setString(2, developer.getLastName());
        statement.setInt(3, developer.getAge());
        statement.setInt(4, developer.getId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Developer developer) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "DELETE FROM developers WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, developer.getId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public int getIndex(Developer developer) throws SQLException {
        Connection connection = DBConnection.getConnection();
        int index = 0;
        String sql = "SELECT id FROM developers WHERE firstName = ? AND lastName = ? AND age = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, developer.getFirstName());
        statement.setString(2, developer.getLastName());
        statement.setInt(3, developer.getAge());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            index = resultSet.getInt("id");
        }
        statement.close();
        connection.close();
        return index;
    }

    @Override
    public void saveWithLanguage(Developer developer, String[] languages) throws SQLException {
        Connection connection = DBConnection.getConnection();
        save(developer);
        int index = getIndex(developer);
        String sql = "INSERT INTO dev_prog_lang (developer_id, prog_lang_id) VALUES (?, ?)";
        for (String language : languages) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, index);
            int idByLanguage = programmingLanguageDAO.getIdByLanguage(language);
            statement.setInt(2, idByLanguage);
            statement.executeUpdate();
            statement.close();
        }
        connection.close();

    }
}
