package com.project.bakery.repository;

import com.project.bakery.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    };

    public List<User> findAll() {
        String query = "SELECT * FROM users";
        List<User> users =
                jdbcTemplate.query(query, new UserRepository.UserMapper());
        return users;
    }

    public User findByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = " + email;
        User user =
                jdbcTemplate.queryForObject(query, new UserRepository.UserMapper());
        return user;
    }

    public void save(User user) {
        String query = "INSERT INTO users (email,password) VALUES (?,?);";
        Object[] data = new Object[]
                { user.getEmail(), user.getPassword()};
        jdbcTemplate.update(query, data);
    }

    public void deleteByEmail(String email) {
        String query = "DELETE FROM users WHERE email = " + email;
        jdbcTemplate.update(query);
    }


    class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i)
                throws SQLException {

            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            User user = new User(email, password);
            return user;
        }
    }

}
