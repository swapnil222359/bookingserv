package com.paypal.bfs.test.bookingserv.repository;

import com.paypal.bfs.test.bookingserv.domain.BookingApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public interface BookingRepository extends JpaRepository<BookingApplication, Integer> {
}
