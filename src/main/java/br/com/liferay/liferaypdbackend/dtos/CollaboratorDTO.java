package br.com.liferay.liferaypdbackend.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CollaboratorDTO {

    @NotBlank
    private String name;

    @NotBlank
    private Boolean isAdministrator;

    @NotBlank
    private String jobRole;
}
