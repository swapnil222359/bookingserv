package com.paypal.bfs.test.bookingserv.service;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

import java.util.List;

public interface BookingService {
    /**
     * Create and persists {@link com.paypal.bfs.test.bookingserv.domain.BookingApplication}
     *
     * @param booking the booking object
     * @return the created booking
     */
    Booking create(Booking booking);

    /**
     * Retrieve and return all the {@link com.paypal.bfs.test.bookingserv.domain.BookingApplication} resources
     *
     * @return list of bookings
     */
    List<Booking> getAllBookings();
}
