package ru.ruben.crud.DAO;

import ru.ruben.crud.model.Developer;
import ru.ruben.crud.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgrammingLanguageDAOImpl implements ProgrammingLanguageDAO{

    private static class SingletonLangDao{
        private static final ProgrammingLanguageDAOImpl INIT = new ProgrammingLanguageDAOImpl();
    }

    public static ProgrammingLanguageDAOImpl getInstance(){
        return ProgrammingLanguageDAOImpl.SingletonLangDao.INIT;
    }

    @Override
    public List<String> findAllLanguage() throws SQLException {
        Connection connection = DBConnection.getConnection();
        List<String> languages = new ArrayList<>();
        String sql = "SELECT * FROM programminglanguage";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            languages.add(resultSet.getString("language_name"));
        }
        statement.close();
        connection.close();
        return languages;
    }

    @Override
    public int getIdByLanguage(String language_name) throws SQLException{
        Connection connection = DBConnection.getConnection();
        int index = 0;
        String sql = "SELECT language_id FROM programminglanguage WHERE language_name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, language_name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            index = resultSet.getInt("language_id");
        }
        statement.close();
        connection.close();
        return index;
    }

    @Override
    public Map<Integer, List<String>> getLanguageByDevelopers(List<Developer> developers) throws SQLException {
        Connection connection = DBConnection.getConnection();
        HashMap<Integer, List<String>> hashMap = new HashMap<>();
        List<String> languages = new ArrayList<>();
        String sql = "SELECT p.language_name " +
                "FROM developers dev " +
                "JOIN dev_prog_lang dpl on dev.id = dpl.developer_id " +
                "JOIN programminglanguage p on p.language_id = dpl.prog_lang_id " +
                "WHERE dev.id = ?";
        for (Developer developer: developers) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, developer.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                languages.add(resultSet.getString("language_name"));
            }
            if (languages.size() != 0) {
                hashMap.put(developer.getId(), new ArrayList<>(languages));
                languages.clear();
            }
            statement.close();
        }

        connection.close();
        return hashMap;
    }

    @Override
    public List<String> findByDeveloper(String id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        List<String> languages = new ArrayList<>();
        String sql = "SELECT p.language_name " +
                "FROM developers dev " +
                "JOIN dev_prog_lang dpl on dev.id = dpl.developer_id " +
                "JOIN programminglanguage p on p.language_id = dpl.prog_lang_id " +
                "WHERE dev.id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            languages.add(resultSet.getString("language_name"));
        }
        statement.close();
        connection.close();
        return languages;
    }

    @Override
    public List<String> findOtherLanguage(String id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        List<String> languages = new ArrayList<>();
        String sql = "SELECT p.language_name FROM programminglanguage p " +
                "where p.language_id not in( " +
                "SELECT prog_lang_id FROM developers " +
                "JOIN dev_prog_lang dpl on developers.id = dpl.developer_id " +
                "WHERE developer_id = ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            languages.add(resultSet.getString("language_name"));
        }
        statement.close();
        connection.close();
        return languages;
    }

    @Override
    public void updateList(String id_dev, String[] languages) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO dev_prog_lang (developer_id, prog_lang_id) VALUES (?, ?)";
        for (String language : languages) {
            int idByLanguage = getIdByLanguage(language);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(id_dev));
            statement.setInt(2, idByLanguage);
            statement.executeUpdate();
            statement.close();
        }
        connection.close();
    }

    @Override
    public void deleteLanguageDeveloper(String id, String language) throws SQLException {
        Connection connection = DBConnection.getConnection();
        int idByLanguage = getIdByLanguage(language);
        String sql = "DELETE FROM dev_prog_lang WHERE developer_id = ? AND prog_lang_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(id));
        statement.setInt(2, idByLanguage);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void saveLanguage(String language) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO programminglanguage (language_name) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, language);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

}
