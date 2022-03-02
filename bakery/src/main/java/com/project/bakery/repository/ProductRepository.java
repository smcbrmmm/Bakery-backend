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
        String query = "INSERT INTO products (productId, productName, price, quantity, img, productDetail, type) VALUES (?, ?, ?, ?, ?, ?, ?);";
        Object[] data = new Object[]
                {product.getProductId(), product.getProductName(), product.getPrice(), product.getQuantity(), product.getImg(), product.getProductDetail(), product.getType()};
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
        String query = "UPDATE products set productName=?, price=?, quantity=?, img=?, productDetail=?, type=? where productId=?";
        Object[] data = new Object[]
                {product.getProductName(), product.getPrice(), product.getQuantity(), product.getImg(), product.getProductDetail(), product.getType(), product.getProductId()};
        jdbcTemplate.update(query, data);
        return product;
    }

    class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int productId = resultSet.getInt("productId");
            String productName = resultSet.getString("productName");
            String img = resultSet.getString("img");
            double price = resultSet.getDouble("price");
            String type = resultSet.getString("type");
            String productDetail = resultSet.getString("productDetail");
            int quantity = resultSet.getInt("quantity");
            Product product = new Product();
            product.setProductId(productId);
            product.setProductName(productName);
            product.setImg(img);
            product.setType(type);
            product.setPrice(price);
            product.setProductDetail(productDetail);
            product.setQuantity(quantity);
            return product;
        }
    }
}
