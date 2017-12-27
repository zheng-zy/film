package com.java1234.entity;

import javax.persistence.*;

/**
 * 管理员类
 *
 * @author user
 */
@Entity
@Table(name = "t_manager")
public class Manager {

    @Id
    @GeneratedValue
    private Integer id; // 编号

    @Column(length = 200)
    private String userName; // 用户名

    @Column(length = 200)
    private String password; // 密码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
