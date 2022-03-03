package com.project.bakery.repository;

import com.project.bakery.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderRepository {

    private JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Order> findAll() {
        String query = "SELECT * FROM orders";
        List<Order> orders =
                jdbcTemplate.query(query, new OrderRepository.OrderMapper());
        return orders;
    }

    public void save(Order order){
        String query = "INSERT INTO orders (user_id, address_id, status) VALUES (?, ?, ?);";
        Object[] data = new Object[]
                { order.getUserId() , order.getAddressId(), order.getStatus()};
        jdbcTemplate.update(query, data);
    }

    class OrderMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int orderId = Integer.parseInt(resultSet.getString("order_id"));
            int userId = Integer.parseInt(resultSet.getString("user_id"));
            int addressId = Integer.parseInt(resultSet.getString("address_id"));
            String status = resultSet.getString("status");

            Order order = new Order();
            order.setOrderId(orderId);
            order.setUserId(userId);
            order.setAddressId(addressId);
            order.setStatus(status);

            return order;
        }
    }

}
