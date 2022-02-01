package com.project.bakery.repository;

import com.project.bakery.entity.Product;
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


    class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int i)
                throws SQLException {

            String name = resultSet.getString("name");
            String img = resultSet.getString("img");
            String price = resultSet.getString("price");
            String type = resultSet.getString("type");
            Product product = new Product(name , img , price , type);
            return product;
        }
    }
}
