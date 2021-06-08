package com.Blog.DAO;

import com.Blog.model.BlogAccount;

import java.sql.Connection;

public interface IAccountManager {
    public void registerNewAccount(BlogAccount blogAccount);

    public BlogAccount findByUsername(String username);

    public boolean checkLogin(String email, String password);

    public BlogAccount findByEmail(String email);

}
