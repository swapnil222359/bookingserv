package com.paypal.bfs.test.bookingserv.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zipCode;
}
