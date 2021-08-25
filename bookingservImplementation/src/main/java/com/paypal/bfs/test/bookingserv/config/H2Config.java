package com.paypal.bfs.test.bookingserv.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.paypal.bfs.test.bookingserv.repository"})
public class H2Config {
}
