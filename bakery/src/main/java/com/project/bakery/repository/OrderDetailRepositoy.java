package com.project.bakery.repository;

import com.project.bakery.model.Address;
import com.project.bakery.model.Order;
import com.project.bakery.model.OrderDetail;
import com.project.bakery.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDetailRepositoy {

    private JdbcTemplate jdbcTemplate;

    public OrderDetailRepositoy(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    };

    public List<OrderDetail> getOrderDetail(String orderId){
        String query = "SELECT * FROM order_details where orderId = " + Integer.parseInt(orderId);
        List<OrderDetail> orderDetails =
                jdbcTemplate.query(query, new OrderDetailRepositoy.OrderDetailMapper());

        List<Integer> list = new ArrayList<>();

        List<Product> productList = new ArrayList<>();

        for(int i=0 ; i<orderDetails.size();i++){
            String query2 = "SELECT * FROM products where id = " + orderDetails.get(i).getProductId();
            productList.add(jdbcTemplate.query(query2, new OrderDetailRepositoy.ProductMapper()).get(0));
        }

        for (Product product: productList) {
            System.out.println(product.toString());
        }

        return orderDetails;

    }


    public void save(OrderDetail orderDetail) {
        String queryGetOrderId = "SELECT * FROM orders WHERE user_id = " + orderDetail.getUserId()  + " ORDER BY order_id"  + " DESC LIMIT 1" ;
        List<Order> orders = jdbcTemplate.query(queryGetOrderId, new OrderDetailRepositoy.OrderMapper());

        String query = "INSERT INTO order_details (productId , productPrice , orderId , productQty) VALUES (?,?,?,?);";
        Object[] data = new Object[]
                { orderDetail.getProductId() , orderDetail.getProductPrice() , orders.get(0).getOrderId() , orderDetail.getProductQty()};
        jdbcTemplate.update(query, data);
//
        // Get product
        String queryForGetProduct = "Select * from products WHERE id=" + orderDetail.getProductId();
        Product product = jdbcTemplate.query(queryForGetProduct, new OrderDetailRepositoy.ProductMapper()).get(0);
        product.setQty(product.getQty()-orderDetail.getProductQty());
        // Update Product
        String queryForUpdateProduct = "UPDATE products set qty=? where id=?";
        Object[] dataForProduct = new Object[]{product.getQty() , product.getId()};
        jdbcTemplate.update(queryForUpdateProduct, dataForProduct);

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

    class OrderDetailMapper implements RowMapper<OrderDetail> {
        @Override
        public OrderDetail mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int productId = Integer.parseInt(resultSet.getString("productId"));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(productId);
            return orderDetail;
        }
    }

    class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int i)
                throws SQLException {
            int productId = resultSet.getInt("id");
            String productName = resultSet.getString("title");
            String img = resultSet.getString("img");
            int price = resultSet.getInt("price");
            String type = resultSet.getString("tag");
            String productDetail = resultSet.getString("description");
            int quantity = resultSet.getInt("qty");
            String title = resultSet.getString("title");
            Product product = new Product();
            product.setId(productId);
            product.setTitle(productName);
            product.setImg(img);
            product.setTag(type);
            product.setPrice(price);
            product.setDescription(productDetail);
            product.setQty(quantity);
            product.setTitle(title);
            return product;
        }
    }

}
