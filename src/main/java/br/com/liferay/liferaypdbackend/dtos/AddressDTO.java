package br.com.liferay.liferaypdbackend.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddressDTO {

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
