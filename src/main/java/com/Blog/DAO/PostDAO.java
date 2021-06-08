package com.Blog.DAO;

import com.Blog.model.Comment;
import com.Blog.model.Post;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PostDAO {
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



    private static final String INSERT_NEW_POST = "INSERT INTO posts(username, title, content, image) VALUES (?,?,?,?);";
    public void addNewPost(Post post){
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_NEW_POST)
                ){
            statement.setString(1,post.getUsername());
            statement.setString(2,post.getTitle());
            statement.setString(3,post.getContent());
            statement.setString(4,post.getImage());
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }



    public void editPost(int post_id,String username, String title, String content, String image, Date time_post){
        String query = "UPDATE posts SET username = ?, title = ?, content = ?, image = ?, time_post = ? WHERE post_id = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
                ){
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,title);
            preparedStatement.setString(3,content);
            preparedStatement.setString(4,image);
            preparedStatement.setDate(5,time_post);
            preparedStatement.setInt(6,post_id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Post> searchPostByTitle(String title){
        String query = "SELECT * FROM posts WHERE title LIKE ? ORDER BY post_id DESC;";
        List<Post> posts = new ArrayList<>();
        String findTitle = "%" + title + "%";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
                ){
            preparedStatement.setString(1,findTitle);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int post_id = resultSet.getInt("post_id");
                String username = resultSet.getString("username");
                String title2 = resultSet.getString("title");
                String content = resultSet.getString("content");
                String image = resultSet.getString("image");
                Date time_post = resultSet.getDate("time_post");
                posts.add(new Post(post_id,username,title2,content,time_post,image));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return posts;
    }

    private static final String SELECT_POST_BY_USERNAME = "SELECT * FROM posts WHERE username = ?;";
    public List<Post> selectPostByUsername(String username){
        List<Post> posts= new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POST_BY_USERNAME)
        ){
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){
                int post_id = resultSet.getInt("post_id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date time_post = resultSet.getDate("time_post");
                String image = resultSet.getString("image");
                posts.add(new Post(post_id,username,title,content,time_post,image));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return posts;
    }

    private static final String SELECT_POST_BY_ID = "SELECT * FROM posts WHERE post_id = ?;";
    public Post selectPostById(int id){
        Post post = null;
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POST_BY_ID)
                ){
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date time_post = resultSet.getDate("time_post");
                String image = resultSet.getString("image");

                post = new Post(id,username,title,content,time_post,image);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return post;
    }

    private static final String DELETE_POST_BY_ID = "DELETE FROM posts WHERE post_id = ?;";
    public boolean deletePostById(int id){
        boolean deleteRow = false;
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POST_BY_ID)
                ){
            preparedStatement.setInt(1,id);
            deleteRow = preparedStatement.executeUpdate()>0;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return deleteRow;
    }

    private static final String SELECT_LATEST_POST_BY_USERNAME = "SELECT * FROM posts WHERE username = ? ORDER BY post_id DESC LIMIT 1;";
    public Post selectLatestPostByUsername(String username){
        Post post = null;
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LATEST_POST_BY_USERNAME);
                ){
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("post_id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date time_post = resultSet.getDate("time_post");
                String image = resultSet.getString("image");

                post = new Post(id,username,title,content,time_post,image);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return post;
    }

    private static final String SELECT_ALL_USER_POSTS = "SELECT * FROM posts WHERE username <> 'admin' ORDER BY post_id DESC";
    public List<Post> selectAllUserPosts(){
        List<Post> posts = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER_POSTS)
                ){
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int post_id = resultSet.getInt("post_id");
                String username = resultSet.getString("username");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String image = resultSet.getString("image");
                Date time_post = resultSet.getDate("time_post");
                List<Comment> comments = selectAllCommentByPostId(post_id);
                posts.add(new Post(post_id,username,title,content,time_post,image,comments));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return posts;
    }

    private List<Comment> selectAllCommentByPostId(int post_id){
        String query = "SELECT * FROM comments WHERE post_id = ?";
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
