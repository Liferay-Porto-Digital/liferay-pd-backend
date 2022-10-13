package br.com.liferay.liferaypdbackend.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class InstitutionDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String registrationNumber;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;

    @NotBlank
    private String url;

    @NotBlank
    private String description;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    @Size(max = 2)
    private String state;

    @NotBlank
    private String number;

    @NotBlank
    @Size(max = 8)
    private String zipCode;
}
