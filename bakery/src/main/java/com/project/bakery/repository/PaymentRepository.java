package com.project.bakery.repository;

import com.project.bakery.model.Payment;
import com.project.bakery.model.Product;
import com.project.bakery.model.UploadSlipRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PaymentRepository {

    private JdbcTemplate jdbcTemplate;

    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void upload(UploadSlipRequest request) {
        String query = "UPDATE payments set paymentSlip=? where orderId=?";
        Object[] data = new Object[]
                {request.getPaymentSlip(),request.getOrderId()};
        jdbcTemplate.update(query, data);

        String query2 = "UPDATE orders set status=\"Waiting for Confirmation\" where order_id=?";
        Object[] data2 = new Object[]
                {request.getOrderId()};
        jdbcTemplate.update(query2, data2);

    }

    public List<Payment> getPayment(String orderId) {
        String query = "SELECT * FROM payments where orderId = " + orderId;
        List<Payment> payments =
                jdbcTemplate.query(query, new PaymentRepository.PaymentMapper());

//        if(payment==null){
//            return new Payment();
//        }

        return payments;
    }

    class PaymentMapper implements RowMapper<Payment> {
        @Override
        public Payment mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int paymentId = resultSet.getInt("paymentId");
            int orderId = resultSet.getInt("orderId");
            int userId = resultSet.getInt("userId");
            String paymentSlip = resultSet.getString("paymentSlip");

            Payment payment = new Payment();
            payment.setPaymentId(paymentId);
            payment.setOrderId(orderId);
            payment.setUserId(userId);
            payment.setPaymentSlip(paymentSlip);

            return payment;
        }
    }
}
