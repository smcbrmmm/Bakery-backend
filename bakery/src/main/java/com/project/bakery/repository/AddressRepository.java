package com.project.bakery.repository;

import com.project.bakery.model.Address;
import com.project.bakery.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AddressRepository {

    private JdbcTemplate jdbcTemplate;

    public AddressRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    };

    public List<Address> findAll(String id) {
        String query = "SELECT * FROM address where user_id = " + Integer.parseInt(id);
        List<Address> addresses =
                jdbcTemplate.query(query, new AddressRepository.AddressMapper());
        return addresses;
    }

    public void save(Address address){
        String query = "INSERT INTO address (user_id , place , house_number , address , reciever_name , recierver_tel, province , postal) VALUES (?,?,?,?,?,?,?,?);";
        Object[] data = new Object[]
                { address.getUserId(), address.getPlace() , address.getHouseNumber()
                        , address.getAddress() , address.getRecieverName()
                        , address.getRecieverTel() , address.getProvince() , address.getPostal()};
        jdbcTemplate.update(query, data);
    }

    public void delete(Address address){
        String query = "DELETE FROM address WHERE address_id = " + address.getAddressId();
        jdbcTemplate.update(query);
    }

    public Address update(Address address) {
        System.out.println(address);
            String query = "UPDATE address set reciever_name=?, recierver_tel=?, postal=?, province=?, place=?, house_number=? , address=? where address_id=?";
            Object[] data = new Object[]
                    {address.getRecieverName(),address.getRecieverTel(),address.getPostal(),address.getProvince(),address.getPlace(),address.getHouseNumber(),address.getAddress(),address.getAddressId()};
            jdbcTemplate.update(query, data);
            return address;
        }

    class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet resultSet, int i)
                throws SQLException {

            String userId = resultSet.getString("user_id");
            String addressId = resultSet.getString("address_id");
            String recieverName = resultSet.getString("reciever_name");
            String recieverTel = resultSet.getString("recierver_tel");
            String postal = resultSet.getString("postal");
            String province = resultSet.getString("province");
            String place = resultSet.getString("place");
            String houseNumber = resultSet.getString("house_number");
            String address = resultSet.getString("address");

            Address addressModel = new Address();
            addressModel.setUserId(Integer.parseInt(userId));
            addressModel.setAddressId(Integer.parseInt(addressId));
            addressModel.setRecieverName(recieverName);
            addressModel.setRecieverTel(recieverTel);
            addressModel.setPostal(postal);
            addressModel.setProvince(province);
            addressModel.setPlace(place);
            addressModel.setHouseNumber(houseNumber);
            addressModel.setAddress(address);

            return addressModel;
        }
    }

}