package com.project.bakery.repository;

import com.project.bakery.model.User;
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
        String query = "INSERT INTO users (email, password, name, role) VALUES (?, ?, ?, ?);";
        Object[] data = new Object[]
                { user.getEmail(), user.getPassword(), user.getName(), "C"};
        jdbcTemplate.update(query, data);
    }

    public void saveLine(User user) {
        String query = "INSERT INTO users (email, tokenId, name, role) VALUES (?, ?, ?, ?);";
        Object[] data = new Object[]
                { user.getEmail(), user.getTokenId(), user.getName(), "CL"};
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
            String name = resultSet.getString("name");
            String tokenId = resultSet.getString("tokenId");
            String role = resultSet.getString("role");

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setTokenId(tokenId);
            user.setRole(role);
            return user;
        }
    }

//    public boolean existsByEmail(String email){
//        String query = "SELECT * FROM users WHERE email = " + email;
//        User user =
//                jdbcTemplate.queryForObject(query, new UserRepository.UserMapper());
//        return ;
//    }

}
