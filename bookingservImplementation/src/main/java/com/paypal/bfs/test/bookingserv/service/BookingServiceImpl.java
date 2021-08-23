package com.paypal.bfs.test.bookingserv.service;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.domain.Address;
import com.paypal.bfs.test.bookingserv.domain.BookingApplication;
import com.paypal.bfs.test.bookingserv.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {


    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking create(Booking booking) {
        bookingRepository.save(createBookingApplication(booking));
        return null;
    }

    @Override
    public List<Booking> getAllBookings() {
        return Optional.ofNullable(bookingRepository.findAll())
                .orElse(new ArrayList<>())
                .stream()
                .map(this::mapToBooking)
                .collect(Collectors.toList());
    }

    private Booking mapToBooking(BookingApplication bookingApplication) {

        Booking booking = new Booking();
        booking.setId(bookingApplication.getId());
        booking.setFirstName(bookingApplication.getFirstName());
        booking.setLastName(bookingApplication.getLastName());
        booking.setCheckinDatetime(bookingApplication.getCheckinTime());
        booking.setCheckoutDatetime(bookingApplication.getCheckoutTime());
        booking.setDateOfBirth(bookingApplication.getDateOfBirth());
        booking.setDeposit(bookingApplication.getDeposit());
        booking.setTotalPrice(bookingApplication.getTotalPrice());
        com.paypal.bfs.test.bookingserv.api.model.Address address = new com.paypal.bfs.test.bookingserv.api.model.Address();
        Optional.ofNullable(bookingApplication.getAddress())
                .ifPresent(savedAddress -> {
                    address.setState(savedAddress.getState());
                    address.setState(savedAddress.getCity());
                    address.setState(savedAddress.getLine1());
                    address.setState(savedAddress.getLine2());
                    address.setState(savedAddress.getZipCode());
                    booking.setAddress(address);
                });

        return booking;
    }

    private BookingApplication createBookingApplication(Booking booking) {
        BookingApplication bookingApplication = new BookingApplication();
        bookingApplication.setFirstName(booking.getFirstName());
        bookingApplication.setLastName(booking.getLastName());
        Address address = new Address();
        address.setState(booking.getAddress().getState());
        bookingApplication.setAddress(address);
        return bookingApplication;


    }
}
