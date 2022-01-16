package ru.ruben.crud.DAO;

import ru.ruben.crud.model.Developer;
import ru.ruben.crud.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoImpl implements DeveloperDao {

    private final ProgrammingLanguageDAO programmingLanguageDAO = ProgrammingLanguageDAOImpl.getInstance();

    private static class SingletonDao {
        private static final DeveloperDaoImpl INIT = new DeveloperDaoImpl();
    }

    public static DeveloperDaoImpl getInstance() {
        return SingletonDao.INIT;
    }

    @Override
    public Developer findById(String id){
        try{
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT * FROM developers WHERE id = ?";
            int id_dev = 0, age = 0;
            String firstName = "", lastName = "";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id_dev = resultSet.getInt("id");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                age = resultSet.getInt("age");
            }
            return new Developer(id_dev, firstName, lastName, age);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Developer> findAll(){
        try{
            Connection connection = DBConnection.getConnection();
            List<Developer> developers = new ArrayList<>();
            String sql = "SELECT * FROM developers";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id_dev = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                Developer developer = new Developer(id_dev, firstName, lastName, age);
                developers.add(developer);
            }
            return developers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Developer developer){
        try{
            Connection connection = DBConnection.getConnection();
            String sql = "INSERT INTO developers (firstName, lastName, age) VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, developer.getFirstName());
            statement.setString(2, developer.getLastName());
            statement.setInt(3, developer.getAge());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Developer developer){
        try{
            Connection connection = DBConnection.getConnection();
            String sql = "UPDATE developers SET firstName = ?, lastName = ?, age = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, developer.getFirstName());
            statement.setString(2, developer.getLastName());
            statement.setInt(3, developer.getAge());
            statement.setInt(4, developer.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Developer developer){
        try{
            Connection connection = DBConnection.getConnection();
            String sql = "DELETE FROM developers WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, developer.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getIndex(Developer developer) {
        int index = 0;
        try{
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT id FROM developers WHERE firstName = ? AND lastName = ? AND age = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, developer.getFirstName());
            statement.setString(2, developer.getLastName());
            statement.setInt(3, developer.getAge());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                index = resultSet.getInt("id");
            }
            return index;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public boolean saveWithLanguage(Developer developer, String[] languages) {
        try{
            Connection connection = DBConnection.getConnection();
            boolean result = false;
            save(developer);
            int index = getIndex(developer);
            String sql = "INSERT INTO dev_prog_lang (developer_id, prog_lang_id) VALUES (?, ?)";
            for (String language : languages) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, index);
                int idByLanguage = programmingLanguageDAO.getIdByLanguage(language);
                statement.setInt(2, idByLanguage);
                result = statement.executeUpdate() > 0;
                //result = statement.executeUpdate() > 0;
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
