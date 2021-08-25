package com.paypal.bfs.test.bookingserv.resource;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingResourceImpl implements BookingResource {

    private BookingService bookingService;

    @Autowired
    public BookingResourceImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public ResponseEntity<Booking> create(Booking booking) {
        return new ResponseEntity<>(bookingService.create(booking), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
