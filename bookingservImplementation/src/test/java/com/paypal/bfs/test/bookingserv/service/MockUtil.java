package com.paypal.bfs.test.bookingserv.service;

import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.domain.BookingApplication;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MockUtil {

    public static Booking getMockBookingRequest() {
        Booking booking =  new Booking();
        booking.setTotalPrice(new BigDecimal("200"));
        booking.setDeposit(new BigDecimal("200"));
        LocalDate dt = LocalDate.parse("2018-11-01");
        booking.setDateOfBirth(dt);
        booking.setFirstName("name");
        booking.setLastName("lastname");
        Instant now = Instant.now();
        booking.setCheckinDatetime(now);
        booking.setCheckoutDatetime(now);
        booking.setAddress(getAddress());
        return booking;
    }

    public static com.paypal.bfs.test.bookingserv.domain.Address getDomainAddress() {
        com.paypal.bfs.test.bookingserv.domain.Address  address = new com.paypal.bfs.test.bookingserv.domain.Address();
        address.setState("state");
        address.setCity("city");
        address.setLine1("line1");
        address.setLine2("line2");
        address.setZipCode("12345");
        return address;
    }

    public static Address getAddress() {
        Address address = new Address();
        address.setState("state");
        address.setCity("city");
        address.setLine1("line1");
        address.setLine2("line2");
        address.setZipcode("12345");
        return address;
    }

    public static BookingApplication getMockBookingApplication(Booking booking) {
        BookingApplication bookingApplication = new BookingApplication();
        bookingApplication.setTotalPrice(booking.getTotalPrice());
        bookingApplication.setDeposit(booking.getDeposit());
        bookingApplication.setCheckoutTime(booking.getCheckoutDatetime());
        bookingApplication.setCheckinTime(booking.getCheckinDatetime());
        bookingApplication.setLastName(booking.getLastName());
        bookingApplication.setFirstName(booking.getFirstName());
        bookingApplication.setDateOfBirth(booking.getDateOfBirth());
        bookingApplication.setAddress(getDomainAddress());
        return bookingApplication;
    }
}
