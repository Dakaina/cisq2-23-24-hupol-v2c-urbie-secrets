package nl.hu.cisq2.hupol.security.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public class AdminRequest {
    @NotBlank
    public String username;
}
