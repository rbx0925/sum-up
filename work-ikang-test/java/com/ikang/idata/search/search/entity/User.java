package com.ikang.idata.search.search.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author rbx
 * @title
 * @Create 2023-07-13 14:19
 * @Description
 */
@Builder
public class User {
    private Integer id;
    private String info;


    public User() {
    }

    private String username;

    public User(Integer id, String info, String username) {
        this.id = id;
        this.info = info;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(info, user.info) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info, username);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
