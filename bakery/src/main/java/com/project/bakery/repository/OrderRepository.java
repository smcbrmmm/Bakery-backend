package com.project.bakery.repository;

import com.project.bakery.model.Order;
import com.project.bakery.model.OrderDetail;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class OrderRepository {

    private JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Order> findAll() {
        String query = "( SELECT orders.order_id, orders.date ,orders.user_id , orders.address_id , orders.status , orders.Date,\n" +
                "SUM(order_details.productPrice * order_details.productQty) as sum " + ", payments.paymentSlip as payment\n" +
                "FROM order_details\n" +
                "INNER JOIN products ON order_details.productId=products.id\n" +
                "INNER JOIN orders ON orders.order_id=order_details.orderId\n" +
                "INNER JOIN payments ON orders.order_id=payments.orderId\n"+
                "GROUP BY order_details.orderId\n" +
                ")";
        List<Order> orders =
                jdbcTemplate.query(query, new OrderRepository.OrderMapper());

        for(int i =0 ; i < orders.size() ; i++) {
//            System.out.println(orders.get(i).getHasPayment());
            if(!"no-slip".equals(orders.get(i).getHasPayment())){
                orders.get(i).setHasPayment("samut");
            }
//            System.out.println();
        }

        return orders;
    }

    public void save(Order order){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        String format = formatter.format(date).replace("2565" , "2022");
        String query = "INSERT INTO orders (user_id, address_id, status , date) VALUES (?, ?, ? , ?);";
        Object[] data = new Object[]
                { order.getUserId() , order.getAddressId(), order.getStatus() , format };
        jdbcTemplate.update(query, data);

        String queryGetOrderId = "SELECT * FROM orders WHERE user_id = " + order.getUserId()  + " ORDER BY order_id"  + " DESC LIMIT 1" ;
        List<Order> orders = jdbcTemplate.query(queryGetOrderId, new OrderRepository.OrderMapperForOrderId());

        String query2 = "INSERT INTO payments (orderId, userId, paymentSlip) VALUES (?, ?, ?);";
        Object[] data2 = new Object[]
                {  orders.get(0).getOrderId(), order.getUserId() , "no-slip"};
        jdbcTemplate.update(query2, data2);
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

    public void cancel(String orderId) {
        String query2 = "SELECT * FROM order_details where orderId =" +  orderId;
        List<OrderDetail> orderDetails =
                jdbcTemplate.query(query2, new OrderRepository.OrderDetailMapper());

        for(int i=0 ; i<orderDetails.size() ;i++){
            String query = "UPDATE products set qty= qty + " + orderDetails.get(i).getProductQty()  + " where id=" + orderDetails.get(i).getProductId();

            jdbcTemplate.update(query);

        }

        String query = "UPDATE orders set status=\"Order Canceled\" where order_id=" + orderId;
        jdbcTemplate.update(query);
    }

    public List<Order> getOrderByDate(String date) {
        String query = "select * FROM orders WHERE date LIKE " + "'" + date + "%'";
        String query2 = "SELECT orders.order_id, orders.date ,orders.user_id , orders.address_id , orders.status , orders.Date ,\n" +
                "SUM(order_details.productPrice * order_details.productQty) as sum, payments.paymentSlip as payment\n" +
                "FROM order_details\n" +
                "INNER JOIN products ON order_details.productId=products.id\n" +
                "INNER JOIN orders ON orders.order_id=order_details.orderId\n" +
                "INNER JOIN payments ON orders.order_id=payments.orderId\n" +
                "WHERE orders.Date LIKE " +"'" + date + "%'" +
                "GROUP BY order_details.orderId";
        System.out.println(query2);
        List<Order> orders =
                jdbcTemplate.query(query2, new OrderRepository.OrderMapper());
        return orders;
    }

    class OrderMapperForOrderId implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int orderId = Integer.parseInt(resultSet.getString("order_id"));

            Order order = new Order();
            order.setOrderId(orderId);

            return order;
        }
    }
    class OrderOGMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int orderId = Integer.parseInt(resultSet.getString("order_id"));
            int userId = Integer.parseInt(resultSet.getString("user_id"));
            int addressId = Integer.parseInt(resultSet.getString("address_id"));
            String status = resultSet.getString("status");
            String date = resultSet.getString("date");

            Order order = new Order();
            order.setOrderId(orderId);
            order.setUserId(userId);
            order.setAddressId(addressId);
            order.setStatus(status);
            order.setDate(date);

            return order;
        }
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
            String payment = resultSet.getString("payment");
            String date = resultSet.getString("date");

            Order order = new Order();
            order.setOrderId(orderId);
            order.setUserId(userId);
            order.setAddressId(addressId);
            order.setStatus(status);
            order.setSumPrice(sumPrice);
            order.setHasPayment(payment);
            order.setDate(date);

            return order;
        }
    }

    class OrderDetailMapper implements RowMapper<OrderDetail> {
        @Override
        public OrderDetail mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int productId = Integer.parseInt(resultSet.getString("productId"));
            int productPrice = resultSet.getInt("productPrice");
            int productQty = resultSet.getInt("productQty");

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(productId);
            orderDetail.setProductPrice(productPrice);
            orderDetail.setProductQty(productQty);
            return orderDetail;
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
