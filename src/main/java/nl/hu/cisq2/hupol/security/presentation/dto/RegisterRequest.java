package nl.hu.cisq2.hupol.security.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank
        String username,

        @Size(min = 12)
        @Pattern(regexp = "^(?=.*\\d{2})(?=.*[a-z]{2})(?=.*[A-Z]{2})(?=.*\\p{P}{2}).*$" , message = "Password should be at least 12 characters and include at least 2 lowercase letters, 2 uppercase letters, 2 digits and 2 punctuation marks")
        String password
) {
}