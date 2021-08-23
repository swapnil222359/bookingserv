package com.paypal.bfs.test.bookingserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BookingServApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookingServApplication.class, args);
    }
}