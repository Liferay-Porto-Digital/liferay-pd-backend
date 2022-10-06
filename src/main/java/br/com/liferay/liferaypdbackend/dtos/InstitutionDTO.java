package br.com.liferay.liferaypdbackend.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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
}
