package com.example.demo.config;

import com.example.demo.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: lituquan
 * Date: 2023/6/27
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User article = new User();
        article.setId(resultSet.getInt("id"));
        article.setName(resultSet.getString("name"));
        article.setAge(resultSet.getInt("age"));
        return article;
    }
}
