package ec.utb.bookingsystem.service;

import ec.utb.bookingsystem.model.Booking;
import ec.utb.bookingsystem.model.BookingStatus;
import ec.utb.bookingsystem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking createBooking(Booking booking) {
        if (booking.getStatus() == null) {
            booking.setStatus(BookingStatus.PENDING);
        }
        return bookingRepository.save(booking);
    }

    public Optional<Booking> updateBooking(Long id, Booking bookingDetails) {
        return bookingRepository.findById(id).map(existing -> {
            existing.setName(bookingDetails.getName());
            existing.setEmail(bookingDetails.getEmail());
            existing.setDateTime(bookingDetails.getDateTime());
            existing.setNumberOfPersons(bookingDetails.getNumberOfPersons());
            if (bookingDetails.getStatus() != null) {
                existing.setStatus(bookingDetails.getStatus());
            }
            return bookingRepository.save(existing);
        });
    }

    public boolean deleteBooking(Long id) {
        return bookingRepository.findById(id).map(existing -> {
            bookingRepository.delete(existing);
            return true;
        }).orElse(false);
    }
}