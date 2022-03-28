package com.project.bakery.repository;

import com.project.bakery.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    private JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        String query = "SELECT * FROM products";
        List<Product> products =
                jdbcTemplate.query(query, new ProductRepository.ProductMapper());
        return products;
    }

    public Product save(Product product) {
        String query = "INSERT INTO products ( title , price, qty, img, description, tag) VALUES ( ?, ?, ?, ?, ?, ?);";
        System.out.println(product.getImg().length());
        Object[] data = new Object[]
                { product.getTitle(), product.getPrice(), product.getQty(), product.getImg(), product.getDescription(), product.getTag()};
        jdbcTemplate.update(query, data);
        return product;
    }

    public Product findByProductId(int productId) {
        String query = "SELECT * FROM products where productId = ?";
        Product product =
                jdbcTemplate.queryForObject(query, new ProductRepository.ProductMapper(), productId);
        return product;
    }

    public Product update(Product product) {
        String query = "UPDATE products set title=?, price=?, qty=?, img=?, description=?, tag=? where id=?";
        Object[] data = new Object[]
                {product.getTitle(), product.getPrice(), product.getQty(), product.getImg(), product.getDescription(), product.getTag(), product.getId()};
        jdbcTemplate.update(query, data);
        return product;
    }

    public void delete(int productId) {
        String query = "DELETE FROM products WHERE id = " + productId;
        jdbcTemplate.update(query);
    }

    public int getProductQty(int productId) {
        String query = "Select * from products where id = " + productId;
        Product product = jdbcTemplate.queryForObject(query , new ProductRepository.ProductMapper());
        return product.getQty();
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
