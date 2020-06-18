package com.example.tiku1.bean;

/**
 * Create by 张瀛煜 on 2020-06-04 ：）
 */
public class LogInBean {


    /**
     * id : 1
     * username : user1
     * password : 123456
     * root : 管理员
     */

    private int id;
    private String username;
    private String password;
    private String root;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
