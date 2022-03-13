package com.project.bakery.repository;

import com.project.bakery.model.Address;
import com.project.bakery.model.Cart;
import com.project.bakery.model.Product;
import com.project.bakery.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository {

    private JdbcTemplate jdbcTemplate;

    public CartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    };

    public List<Cart> inCart(String userId) {
        String query = "SELECT * FROM carts where userId = " + userId ;
        List<Cart> carts =
                jdbcTemplate.query(query, new CartRepository.CartMapper());

        for(int i=0; i< carts.size(); i++){
            String query2 = "SELECT * FROM products where id = " + carts.get(i).getProductId();
            Product product =
                jdbcTemplate.queryForObject(query2, new CartRepository.ProductMapper());
            carts.get(i).setId(product.getId());
            carts.get(i).setTitle(product.getTitle());
            carts.get(i).setDescription(product.getDescription());
            carts.get(i).setImg(product.getImg());
            carts.get(i).setTag(product.getTag());
            carts.get(i).setPrice(product.getPrice());
        }

        return carts;
    }

    public void insert(Cart cart) {
        String queryForInCart = "SELECT * FROM carts where userId = " + cart.getUserId() + " and productId =" + cart.getProductId() ;
        List<Cart> carts =
                jdbcTemplate.query(queryForInCart, new CartRepository.CartMapper());

        if(carts.size() == 0){
            String query = "INSERT INTO carts (userId , productId , productQty) VALUES ( ? , ? , ?);";
            Object[] data = new Object[]
                    { cart.getUserId() , cart.getProductId() , cart.getQty()};
            jdbcTemplate.update(query, data);
        }else{



            String query = "UPDATE carts set productQty=? where userId=" + carts.get(0).getUserId() + " and productId =" + carts.get(0).getProductId();
            System.out.println(query);
            Object[] data = new Object[]
                    {carts.get(0).getQty()+1};
            jdbcTemplate.update(query, data);
        }

    }

    public void deleteItemInCart(Cart cart) {
        System.out.println("deleteInCart");

        String query = "DELETE FROM carts WHERE userId = " +cart.getUserId() + " and productId = " + cart.getProductId();
        jdbcTemplate.update(query);

    }

    public void deleteAllItemInCart(Cart cart) {
        String query = "DELETE FROM carts WHERE userId = " +cart.getUserId();
        jdbcTemplate.update(query);
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

    class CartMapper implements RowMapper<Cart> {
        @Override
        public Cart mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int productQty = resultSet.getInt("productQty");
            int productId = resultSet.getInt("productId");
            int userId = resultSet.getInt("userId");


            Cart cart = new Cart();
            cart.setQty(productQty);
            cart.setProductId(productId);
            cart.setUserId(userId);

            return cart;
        }
    }

}
