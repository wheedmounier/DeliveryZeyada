package com.ahmed.deliveryzeyada.data.Remote.api.login.model;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public class User
{
    private String userName , password;

    public User(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
