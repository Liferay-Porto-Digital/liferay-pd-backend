package br.com.liferay.liferaypdbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private String description;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    @Size(max = 2)
    private String state;

    @NotBlank
    @Size(max = 8)
    private String zipCode;
}
