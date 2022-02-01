package com.project.bakery.repository;

import com.project.bakery.entity.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {

    private JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> findAll() {
        String query = "SELECT * FROM customer";
        List<Customer> customers =
                jdbcTemplate.query(query, new CustomerMapper());
        return customers;
    }

    public Customer findById(int id) {
        String query = "SELECT * FROM customer WHERE id = " + id;
        Customer customer =
                jdbcTemplate.queryForObject(query, new CustomerMapper());
        return customer;
    }

    public void save(Customer customer) {
        String query = "INSERT INTO customer (id,name,pin) VALUES (?,?,?);";
        Object[] data = new Object[]
                { customer.getId(), customer.getName(), customer.getPin() };
        jdbcTemplate.update(query, data);
    }

    public void deleteById(int id) {
        String query = "DELETE FROM customer WHERE id = " + id;
        jdbcTemplate.update(query);
    }

    class CustomerMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String pin = resultSet.getString("pin");

            Customer customer = new Customer(id, name, pin);
            return customer;
        }
    }



}
