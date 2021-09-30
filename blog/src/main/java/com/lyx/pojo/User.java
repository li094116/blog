package com.lyx.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//表名
@Entity(name = "t_user")
public class User {
    @Id//设置主键
    @GeneratedValue//自动递增
    private Long id;
    private String nickName;
    private String userName;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "user")//指定关系 一个user对应多个blog
    private List<Blog> blogs = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nikeName) {
        this.nickName = nikeName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "{\"User\":{"
                + "\"id\":"
                + id
                + ",\"nickName\":\""
                + nickName + '\"'
                + ",\"userName\":\""
                + userName + '\"'
                + ",\"password\":\""
                + password + '\"'
                + ",\"email\":\""
                + email + '\"'
                + ",\"avatar\":\""
                + avatar + '\"'
                + ",\"type\":"
                + type
                + ",\"createTime\":\""
                + createTime + '\"'
                + ",\"updateTime\":\""
                + updateTime + '\"'
                + "}}";

    }
}
