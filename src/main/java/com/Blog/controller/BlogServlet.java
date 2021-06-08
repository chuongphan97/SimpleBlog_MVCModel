package com.Blog.controller;

import com.Blog.DAO.AccountManager;
import com.Blog.DAO.CommentDAO;
import com.Blog.DAO.PostDAO;
import com.Blog.model.BlogAccount;
import com.Blog.model.Comment;
import com.Blog.model.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BlogServlet", value = "/blogs")
public class BlogServlet extends HttpServlet {

    private static final AccountManager accountManager;
    private static final PostDAO postDAO;
    private static final CommentDAO commentDAO;
    static {
        commentDAO = new CommentDAO();
        accountManager = new AccountManager();
        postDAO = new PostDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){

            case "edit":
                showEditForm(request,response);
                break;
            case "delete-post":
                deletePost(request,response);
                break;
            case "wall":
                showWall(request,response);
                break;
            case "delete-comment":
                deleteComment(request,response);
                break;
            case "detail-post":
                showDetailPost(request,response);
                break;
            case "create":
                showAddNewPostForm(request,response);
                break;
            case "home":
                showHomePage(request,response);
                break;
            case "admin":
                showAdminPage(request,response);
                break;
            case "register":
                showRegisterForm(request,response);
                break;
            case "logout":
                logOut(request,response);
                break;
            default:
                showLoginForm(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "search":
                searchByTitle(request,response);
                break;
            case "edit":
                editPost(request,response);
                break;
            case "add-comment":
                addComment(request,response);
                break;
            case "add":
                addNewPost(request,response);
            case "addPost":
                addAdminPost(request,response);
                break;
            case "register":
                registerNewAccount(request,response);
                break;
            case "login":
                login(request,response);
                break;
        }
    }

    public void searchByTitle(HttpServletRequest request, HttpServletResponse response){
        String search = request.getParameter("search");
        List<Post> posts= postDAO.searchPostByTitle(search);
        HttpSession session = request.getSession();
        BlogAccount account = (BlogAccount) session.getAttribute("account");
        request.setAttribute("account",account);
        request.setAttribute("posts",posts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editPost(HttpServletRequest request, HttpServletResponse response){
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        String username = request.getParameter("username");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String image = request.getParameter("image");
        Date time_post = Date.valueOf(request.getParameter("time_post"));
        postDAO.editPost(post_id,username,title,content,image,time_post);

        HttpSession session = request.getSession();
        BlogAccount account = (BlogAccount) session.getAttribute("account");
        List<Comment> comments = commentDAO.selectAllCommentByPostId(post_id);
        Post post = postDAO.selectPostById(post_id);
        request.setAttribute("account",account);
        request.setAttribute("comments",comments);
        request.setAttribute("detailPost",post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("detail-post.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response){
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        HttpSession session = request.getSession();
        BlogAccount account = (BlogAccount) session.getAttribute("account");
        Post post = postDAO.selectPostById(post_id);
        request.setAttribute("account",account);
        request.setAttribute("detailPost",post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePost(HttpServletRequest request, HttpServletResponse response){
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        postDAO.deletePostById(post_id);
        HttpSession session = request.getSession();
        BlogAccount blogAccount = (BlogAccount) session.getAttribute("account");
        Post post = postDAO.selectLatestPostByUsername("admin");
        List<Post> posts = postDAO.selectAllUserPosts();
        request.setAttribute("posts",posts);
        request.setAttribute("adPost",post);
        request.setAttribute("account",blogAccount);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showWall(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        List<Post> posts= postDAO.selectPostByUsername(username);
        HttpSession session = request.getSession();
        BlogAccount account = (BlogAccount) session.getAttribute("account");
        request.setAttribute("account",account);
        request.setAttribute("posts",posts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("wall.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteComment(HttpServletRequest request, HttpServletResponse response){
        int comment_id = Integer.parseInt(request.getParameter("comment_id"));
        int post_id = commentDAO.findPostIdByCommentId(comment_id);
        commentDAO.deleteComment(comment_id);
        HttpSession session = request.getSession();
        BlogAccount account = (BlogAccount) session.getAttribute("account");
        List<Comment> comments = commentDAO.selectAllCommentByPostId(post_id);
        Post post = postDAO.selectPostById(post_id);
        request.setAttribute("account",account);
        request.setAttribute("comments",comments);
        request.setAttribute("detailPost",post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("detail-post.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showDetailPost(HttpServletRequest request, HttpServletResponse response){
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        HttpSession session = request.getSession();
        BlogAccount account = (BlogAccount) session.getAttribute("account");
        List<Comment> comments = commentDAO.selectAllCommentByPostId(post_id);
        Post post = postDAO.selectPostById(post_id);
        request.setAttribute("account",account);
        request.setAttribute("comments",comments);
        request.setAttribute("detailPost",post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("detail-post.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public void showAddNewPostForm(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        BlogAccount blogAccount = (BlogAccount) session.getAttribute("account");
        request.setAttribute("account",blogAccount);
        RequestDispatcher dispatcher = request.getRequestDispatcher("create-form.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showHomePage(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        BlogAccount blogAccount = (BlogAccount) session.getAttribute("account");
        Post post = postDAO.selectLatestPostByUsername("admin");
        List<Post> posts = postDAO.selectAllUserPosts();
        request.setAttribute("posts",posts);
        request.setAttribute("adPost",post);
        request.setAttribute("account",blogAccount);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showAdminPage(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        BlogAccount blogAccount = (BlogAccount) session.getAttribute("account");
        request.setAttribute("account",blogAccount);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void logOut(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showLoginForm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);
    }

    public void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request,response);
    }
    
    
    
    public void addComment(HttpServletRequest request, HttpServletResponse response){
        int post_id = Integer.parseInt(request.getParameter("post_id"));
        String username = request.getParameter("username");
        String content = request.getParameter("content");
        commentDAO.addComment(new Comment(post_id,username,content));
        List<Comment> comments = commentDAO.selectAllCommentByPostId(post_id);
        HttpSession session = request.getSession();
        Post post = postDAO.selectPostById(post_id);
        BlogAccount account = (BlogAccount) session.getAttribute("account");
        request.setAttribute("detailPost",post);
        request.setAttribute("account",account);
        request.setAttribute("comments",comments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("detail-post.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    public void addAdminPost(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        String image = request.getParameter("b64");
        postDAO.addNewPost(new Post(username,title,content,image));

        request.setAttribute("msg","Add new post successful!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("create-form.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addNewPost(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String image = request.getParameter("b64");
        postDAO.addNewPost(new Post(username,title,content,image));
        List<Post> postList = postDAO.selectAllUserPosts();
        HttpSession session = request.getSession();
        request.setAttribute("posts",postList);
        request.setAttribute("account",session.getAttribute("account"));
        request.setAttribute("adPost",session.getAttribute("adPost"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }





    public void registerNewAccount(HttpServletRequest request,HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        BlogAccount blogAccount = new BlogAccount(username,password,dob,gender,email,phoneNumber);

        accountManager.registerNewAccount(blogAccount);
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (accountManager.checkLogin(email,password)) {
            BlogAccount account = accountManager.findByEmail(email);
            Post post = postDAO.selectLatestPostByUsername("admin");
            List<Post> posts = postDAO.selectAllUserPosts();
            HttpSession session = request.getSession();

            session.setAttribute("adPost",post);
            session.setAttribute("account", account);
            request.setAttribute("posts",posts);
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            try {
                dispatcher.forward(request,response);
            } catch (ServletException | IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            if (accountManager.findByEmail(email) == null){
                request.setAttribute("msg","Your email doesn't exist!");
            } else {
                request.setAttribute("msg","Wrong password!");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            try {
                dispatcher.forward(request,response);
            } catch (ServletException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
