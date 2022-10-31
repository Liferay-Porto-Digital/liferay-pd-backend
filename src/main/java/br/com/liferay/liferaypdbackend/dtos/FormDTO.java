package br.com.liferay.liferaypdbackend.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class FormDTO {

    @NotBlank
    private String institutionName;

    private String institutionRegistrationNumber;

    private String institutionPhoneNumber;

    private String institutionEmail;

    private String institutionUrl;

    private String institutionStreet;

    private String institutionCity;

    @Size(max = 2)
    private String institutionState;

    @Size(max = 8)
    private String institutionZipCode;

    @NotBlank
    private List<String> objective;

    @NotBlank
    private List<String> vulnerability;

    @NotBlank
    private String dateOfEvent;

    @NotBlank
    private String nameContact;

    @NotBlank
    private String lastNameContact;

    @NotBlank
    private Double value;
}
