package com.project.bakery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        // TODO:
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers("/api/user/register", "/api/user/register/line"
                        , "/api/products/allProducts" , "/api/address/address/{user_id}"
                ,"/api/order/order" , "/api/address/save" , "/api/address/delete" , "/api/address/update" ,
                "/api/products/delete" , "/api/user/login" , "/api/products/insert" , "/api/order/save" , "/api/orderDetail/save" ,
                "/api/orderDetail/order/{orderId}" , "/api/user/isHave/{email}" , "/api/user/loginbyline/{email}" ,
                "/api/orderDetail/orderdetail/{orderId}" , "/api/order/getSumPrice/{orderId}" , "/api/cart/inCart/{userId}" ,
                "/api/cart/inCart/insert" , "/api/cart/inCart/deleteItemInCart" , "/api/cart/inCart/deleteAllItem" ,
                "/api/address/orderAddress/{addressId}" , "/api/order/cancel/{orderId}" , "/api/payment/upload" ,
                "/api/payment/getPayment/{orderId}" , "/api/order/getOrderByDate/{date}"
                ).anonymous()
                .anyRequest().authenticated();
    }

}
