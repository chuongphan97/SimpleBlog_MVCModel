package com.Blog.DAO;

import com.Blog.model.BlogAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountManager implements IAccountManager{

    private String jdbcURL = "jdbc:mysql://localhost:3306/case_study_module3?useSSL=false";
    private String jdbcUsername = "root";
    private String jbdcPasword = "061097";

    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jbdcPasword);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
    private static final String REGISTER_ACCOUNT = "INSERT INTO blog_accounts(username, password, dob, gender, email, phoneNumber) VALUES (?,?,?,?,?,?)";
    @Override
    public void registerNewAccount(BlogAccount blogAccount) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_ACCOUNT);
                ) {
            preparedStatement.setString(1, blogAccount.getUsername());
            preparedStatement.setString(2, blogAccount.getPassword());
            preparedStatement.setDate(3, blogAccount.getDob());
            preparedStatement.setString(4,blogAccount.getGender());
            preparedStatement.setString(5,blogAccount.getEmail());
            preparedStatement.setString(6,blogAccount.getPhoneNumber());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static final String SELECT_BY_USER_NAME = "SELECT username ,password, dob, gender, email, phoneNumber FROM blog_accounts WHERE username = ?;";

    @Override
    public BlogAccount findByUsername(String username) {

        BlogAccount blogAccount = null;
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_NAME);
                ) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String password = resultSet.getString("password");
                Date dob = resultSet.getDate("dob");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                blogAccount = new BlogAccount(username,password,dob,gender,email,phoneNumber);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return blogAccount;
    }
    private static final String SELECT_PASSWORD_BY_EMAIL = "SELECT password FROM blog_accounts WHERE email = ?;";
    @Override
    public boolean checkLogin(String email, String password) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PASSWORD_BY_EMAIL)
                ) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String checkPassword = resultSet.getString("password");
                if (checkPassword.equals(password)) return true;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }


    private static final String SELECT_BY_EMAIL = "SELECT username,password,dob,gender,email,phoneNumber FROM blog_accounts WHERE email = ?;";
    @Override
    public BlogAccount findByEmail(String email) {
        BlogAccount blogAccount = null;
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL)
                ){
            System.out.println(preparedStatement);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Date dob = resultSet.getDate("dob");
                String gender = resultSet.getString("gender");
                String phoneNumber = resultSet.getString("phoneNumber");
                blogAccount = new BlogAccount(username,password,dob,gender,email,phoneNumber);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return blogAccount;
    }
}
