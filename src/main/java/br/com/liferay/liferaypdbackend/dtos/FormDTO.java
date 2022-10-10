package br.com.liferay.liferaypdbackend.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FormDTO {

//    TODO: Analyse better implementation of the following logic (Maybe ENUM?)
//    @ElementCollection
//    private List<String> objective;
//
//    @ElementCollection
//    private List<String> vulnerability;

    @NotBlank
    private LocalDateTime dateOfCreation;

    @NotBlank
    private LocalDate dateOfEvent;

    @NotBlank
    private String nameContact;

    @NotBlank
    private String lastNameContact;

    @NotBlank
    private String type;

    @NotBlank
    private double value;
}
