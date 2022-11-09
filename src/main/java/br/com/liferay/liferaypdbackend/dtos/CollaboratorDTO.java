package br.com.liferay.liferaypdbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CollaboratorDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String jobRole;

    @NotBlank
    private Boolean isAdministrator;
}
