package com.seller.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJdbcHttpSession
public class SellerPortalApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SellerPortalApplication.class, args);
    }
}