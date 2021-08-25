package com.paypal.bfs.test.bookingserv.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.service.BookingService;
import com.paypal.bfs.test.bookingserv.service.MockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookingResourceImpl.class)
public class BookingResourceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void shouldGetBookingsReturn200() throws Exception {
        Mockito.when(bookingService.getAllBookings()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/v1/bfs/booking"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateBookingsReturn200() throws Exception {
        Booking mockBookingRequest = MockUtil.getMockBookingRequest();
        Mockito.when(bookingService.create(mockBookingRequest)).thenReturn(new Booking());
        this.mockMvc.perform(post("/v1/bfs/booking")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(mockBookingRequest)))
        .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldCreateBookingsReturn400MissingAddress() throws Exception {
        Booking mockBookingRequest = MockUtil.getMockBookingRequest();
        mockBookingRequest.setAddress(null);
        Mockito.when(bookingService.create(mockBookingRequest)).thenReturn(new Booking());
        this.mockMvc.perform(post("/v1/bfs/booking")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mockBookingRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateBookingsReturn400MissingName() throws Exception {
        Booking mockBookingRequest = MockUtil.getMockBookingRequest();
        mockBookingRequest.setFirstName(null);
        Mockito.when(bookingService.getAllBookings()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(post("/v1/bfs/booking")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mockBookingRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateBookingsReturn400MissingLastName() throws Exception {
        Booking mockBookingRequest = MockUtil.getMockBookingRequest();
        mockBookingRequest.setLastName(null);
        Mockito.when(bookingService.getAllBookings()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(post("/v1/bfs/booking")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mockBookingRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateBookingsReturn400MissingAddressLIne1() throws Exception {
        Booking mockBookingRequest = MockUtil.getMockBookingRequest();
        mockBookingRequest.getAddress().setLine1(null);
        Mockito.when(bookingService.getAllBookings()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(post("/v1/bfs/booking")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mockBookingRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}