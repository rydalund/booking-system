package ec.utb.bookingsystem.service;

import ec.utb.bookingsystem.dto.BookingDTO;
import ec.utb.bookingsystem.dto.CreateBookingRequest;
import ec.utb.bookingsystem.dto.UpdateBookingRequest;
import ec.utb.bookingsystem.model.Booking;
import ec.utb.bookingsystem.model.BookingStatus;
import ec.utb.bookingsystem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    private BookingDTO convertToDTO(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getName(),
                booking.getEmail(),
                booking.getDateTime(),
                booking.getNumberOfPersons(),
                booking.getStatus()
        );
    }

    private Booking convertToEntity(CreateBookingRequest request) {
        Booking booking = new Booking();
        booking.setName(request.getName());
        booking.setEmail(request.getEmail());
        booking.setDateTime(request.getDateTime());
        booking.setNumberOfPersons(request.getNumberOfPersons());
        booking.setStatus(BookingStatus.PENDING); // Default status
        return booking;
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<BookingDTO> getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(this::convertToDTO);
    }

    public BookingDTO createBooking(CreateBookingRequest request) {
        Booking booking = convertToEntity(request);
        Booking savedBooking = bookingRepository.save(booking);
        return convertToDTO(savedBooking);
    }

    public Optional<BookingDTO> updateBooking(Long id, UpdateBookingRequest request) {
        return bookingRepository.findById(id).map(existing -> {
            existing.setName(request.getName());
            existing.setEmail(request.getEmail());
            existing.setDateTime(request.getDateTime());
            existing.setNumberOfPersons(request.getNumberOfPersons());
            if (request.getStatus() != null) {
                existing.setStatus(request.getStatus());
            }
            Booking updatedBooking = bookingRepository.save(existing);
            return convertToDTO(updatedBooking);
        });
    }

    public boolean deleteBooking(Long id) {
        return bookingRepository.findById(id).map(existing -> {
            bookingRepository.delete(existing);
            return true;
        }).orElse(false);
    }
}