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

//    public void saveLine(User user) {
//        String query = "INSERT INTO users (email, tokenId, name, role) VALUES (?, ?, ?, ?);";
//        Object[] data = new Object[]
//                { user.getEmail(), user.getTokenId(), user.getName(), "CL"};
//        jdbcTemplate.update(query, data);
//    }

    public void deleteByEmail(String email) {
        String query = "DELETE FROM users WHERE email = " + email;
        jdbcTemplate.update(query);
    }

    public User login(String email, String password) {
        String query = "SELECT * FROM users WHERE email = " + "\"" + email + "\"" + " and password = " + "\"" + password + "\"";
        User user =
                jdbcTemplate.queryForObject(query, new UserRepository.UserMapper());
        return user;
    }


    class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int id = Integer.parseInt(resultSet.getString("id"));
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            String img = resultSet.getString("img");

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setId(id);
            user.setImg(img);
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
