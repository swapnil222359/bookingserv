package com.paypal.bfs.test.bookingserv.service;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.domain.BookingApplication;
import com.paypal.bfs.test.bookingserv.repository.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static com.paypal.bfs.test.bookingserv.service.MockUtil.getMockBookingRequest;
import static com.paypal.bfs.test.bookingserv.service.MockUtil.getMockBookingApplication;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;


class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;
    private BookingService bookingService;

    @BeforeEach
    public void setup() {
        initMocks(this);
        bookingService = new BookingServiceImpl(bookingRepository);
    }

    @Test
    public void testCreateBooking() {
        Booking booking = getMockBookingRequest();
        BookingApplication bookingApplication = getMockBookingApplication(booking);

        Mockito.when(bookingRepository.save(any(BookingApplication.class)))
                .thenReturn(bookingApplication);

        Booking response = bookingService.create(booking);
        Assertions.assertEquals(response, booking);

    }

    @Test
    public void testGetAllBookings() {
        Booking booking = getMockBookingRequest();
        BookingApplication bookingApplication = getMockBookingApplication(booking);

        Mockito.when(bookingRepository.findAll())
                .thenReturn(Arrays.asList(bookingApplication));

        List<Booking> response = bookingService.getAllBookings();
        Assertions.assertEquals(response.get(0), booking);

    }

    @Test
    public void testGetAllBookingsReturnEmptyList() {
        Mockito.when(bookingRepository.findAll()).thenReturn(null);
        Assertions.assertEquals(0,bookingService.getAllBookings().size());
    }



}