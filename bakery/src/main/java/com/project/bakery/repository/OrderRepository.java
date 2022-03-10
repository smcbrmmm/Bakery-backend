package com.project.bakery.repository;

import com.project.bakery.model.Order;
import org.springframework.data.relational.core.sql.In;
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
        String query = "( SELECT orders.order_id, orders.user_id , orders.address_id , orders.status , orders.Date,\n" +
                "SUM(order_details.productPrice * order_details.productQty) as sum \n" +
                "FROM order_details\n" +
                "INNER JOIN products ON order_details.productId=products.id\n" +
                "INNER JOIN orders ON orders.order_id=order_details.orderId\n" +
                "GROUP BY order_details.orderId\n" +
                ")";
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

    public List<Integer>  getSumPrice(String orderId) {
        String query = "SELECT \n" +
                "SUM(order_details.productPrice * order_details.productQty) as sum \n" +
                "FROM order_details\n" +
                "INNER JOIN products ON order_details.productId=products.id\n" +
                "INNER JOIN orders ON orders.order_id=order_details.orderId \n" +
                "WHERE order_details.orderId=" + orderId;

        List<Integer> result = jdbcTemplate.query(query, new OrderRepository.PriceMapper());
        return result;
    }

    class OrderMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int orderId = Integer.parseInt(resultSet.getString("order_id"));
            int userId = Integer.parseInt(resultSet.getString("user_id"));
            int addressId = Integer.parseInt(resultSet.getString("address_id"));
            String status = resultSet.getString("status");
            int sumPrice = resultSet.getInt("sum");

            Order order = new Order();
            order.setOrderId(orderId);
            order.setUserId(userId);
            order.setAddressId(addressId);
            order.setStatus(status);
            order.setSumPrice(sumPrice);

            return order;
        }
    }

    class PriceMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int price = resultSet.getInt("sum");
            return price;
        }
    }

}
