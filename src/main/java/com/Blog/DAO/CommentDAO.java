package com.Blog.DAO;

import com.Blog.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
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

    public void addComment(Comment comment){
        String query = "INSERT INTO comments(post_id, username, content) VALUES(?,?,?);";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){
            preparedStatement.setInt(1,comment.getPost_id());
            preparedStatement.setString(2,comment.getUsername());
            preparedStatement.setString(3,comment.getContent());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int findPostIdByCommentId(int comment_id){
        String query = "SELECT post_id FROM comments WHERE comment_id = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
                ){
            preparedStatement.setInt(1,comment_id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getInt("post_id");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void deleteComment(int comment_id){
        String query = "DELETE FROM comments WHERE comment_id = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
                ){
            preparedStatement.setInt(1, comment_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Comment> selectAllCommentByPostId(int post_id){
        String query = "SELECT * FROM comments WHERE post_id = ? ORDER BY comment_id;";
        List<Comment> comments = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
                ){
            preparedStatement.setInt(1,post_id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int comment_id = resultSet.getInt("comment_id");
                String username = resultSet.getString("username");
                String content = resultSet.getString("content");
                Date time_comment = resultSet.getDate("time_comment");
                comments.add(new Comment(comment_id,post_id,username,content,time_comment));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return comments;
    }
}
