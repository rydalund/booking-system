package ec.utb.bookingsystem.dto;

import ec.utb.bookingsystem.model.BookingStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class BookingDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime dateTime;
    private Integer numberOfPersons;
    private BookingStatus status;

    public BookingDTO() {}

    public BookingDTO(Long id, String name, String email, LocalDateTime dateTime,
                      Integer numberOfPersons, BookingStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateTime = dateTime;
        this.numberOfPersons = numberOfPersons;
        this.status = status;
    }
}