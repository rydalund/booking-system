package ec.utb.bookingsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class CreateBookingRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Valid email is required")
    private String email;

    @NotNull(message = "Date / time is required")
    private LocalDateTime dateTime;

    @Min(value = 1, message = "Persons is required")
    private Integer numberOfPersons;

    public CreateBookingRequest() {}

    public CreateBookingRequest(String name, String email, LocalDateTime dateTime, Integer numberOfPersons) {
        this.name = name;
        this.email = email;
        this.dateTime = dateTime;
        this.numberOfPersons = numberOfPersons;
    }
}