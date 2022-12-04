package br.com.liferay.liferaypdbackend.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    private Boolean disasterObjective = false;

    private Boolean suppliesObjective = false;

    private Boolean healthObjective = false;

    private Boolean educationObjective = false;

    private Boolean justiceObjective = false;

    private Boolean professionalObjective = false;

    private String otherObjective = "";

    private Boolean monetaryVulnerability = false;

    private Boolean healthVulnerability = false;

    private Boolean homelessVulnerability = false;

    private String otherVulnerability = "";

    @NotBlank
    private String dateOfEvent;

    @NotBlank
    private String nameContact;

    @NotBlank
    private String lastNameContact;

    @NotBlank
    private Double value;
}
