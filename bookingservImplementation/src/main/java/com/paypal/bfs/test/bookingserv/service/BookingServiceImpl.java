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
        return Optional.ofNullable(bookingRepository.save(createBookingApplication(booking)))
                .map(this::mapToBooking)
                .orElseThrow(() -> new RuntimeException("Cannot save entity"));
    }

    @Override
    public List<Booking> getAllBookings() {
        return Optional.ofNullable(bookingRepository.findAll())
                .orElse(new ArrayList<>())
                .stream()
                .map(this::mapToBooking)
                .collect(Collectors.toList());
    }

    private Booking mapToBooking(BookingApplication application) {

        Booking booking = new Booking();
        booking.setId(application.getId());
        booking.setFirstName(application.getFirstName());
        booking.setLastName(application.getLastName());
        booking.setCheckinDatetime(application.getCheckinTime());
        booking.setCheckoutDatetime(application.getCheckoutTime());
        booking.setDateOfBirth(application.getDateOfBirth());
        booking.setDeposit(application.getDeposit());
        booking.setTotalPrice(application.getTotalPrice());
        com.paypal.bfs.test.bookingserv.api.model.Address address = new com.paypal.bfs.test.bookingserv.api.model.Address();
        Optional.ofNullable(application.getAddress())
                .ifPresent(savedAddress -> {
                    address.setState(savedAddress.getState());
                    address.setCity(savedAddress.getCity());
                    address.setLine1(savedAddress.getLine1());
                    address.setLine2(savedAddress.getLine2());
                    address.setZipcode(savedAddress.getZipCode());
                    booking.setAddress(address);
                });

        return booking;
    }

    private BookingApplication createBookingApplication(Booking booking) {

        BookingApplication bookingApplication = new BookingApplication();
        bookingApplication.setFirstName(booking.getFirstName());
        bookingApplication.setLastName(booking.getLastName());
        bookingApplication.setCheckinTime(booking.getCheckinDatetime());
        bookingApplication.setCheckoutTime(booking.getCheckoutDatetime());
        bookingApplication.setTotalPrice(booking.getTotalPrice());
        bookingApplication.setDateOfBirth(booking.getDateOfBirth());
        bookingApplication.setDeposit(booking.getDeposit());
        Optional.ofNullable(booking.getAddress())
                .ifPresent(addressRequest -> {
                    Address address = new Address();
                    address.setState(addressRequest.getState());
                    address.setCity(addressRequest.getCity());
                    address.setLine1(addressRequest.getLine1());
                    address.setLine2(addressRequest.getLine2());
                    address.setZipCode(addressRequest.getZipcode());
                    bookingApplication.setAddress(address);
                });
        return bookingApplication;
    }
}
