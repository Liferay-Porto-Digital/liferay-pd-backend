package br.com.liferay.liferaypdbackend.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class FormDTO {

    @NotBlank
    private String institutionName;

    @NotBlank
    private String institutionRegistrationNumber;

    @NotBlank
    private String institutionPhoneNumber;

    @NotBlank
    private String institutionEmail;

    @NotBlank
    private String institutionUrl;

    @NotBlank
    private String institutionStreet;

    @NotBlank
    private String institutionCity;

    @NotBlank
    @Size(max = 2)
    private String institutionState;

    @NotBlank
    @Size(max = 8)
    private String institutionZipCode;

    @NotBlank
    private List<String> objective;

    @NotBlank
    private List<String> vulnerability;

    @NotBlank
    private LocalDate dateOfEvent;

    @NotBlank
    private String nameContact;

    @NotBlank
    private String lastNameContact;

    @NotBlank
    private Double value;
}
