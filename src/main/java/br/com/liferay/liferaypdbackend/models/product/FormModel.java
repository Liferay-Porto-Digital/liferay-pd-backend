package br.com.liferay.liferaypdbackend.models.product;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Model class for all forms
 */
@Entity
@Table(name = "FormModel")
@Data
@NoArgsConstructor
public abstract class FormModel {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID Collaborator;

    private UUID Institution;

//    TODO: Analyse better implementation of the following logic
//    @ElementCollection
//    private List<String> objective;
//
//    @ElementCollection
//    private List<String> vulnerability;

    private LocalDateTime dateOfCreation;

    private LocalDate dateOfEvent;

    private String nameContact;

    private String lastNameContact;

    private String type;

    private double value;
    //endregion

    //region CONSTRUCTORS
    public FormModel(String typeOfForm, CollaboratorModel formCreator, InstitutionModel destinedInstitutionModel, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Double value) {
        this.dateOfCreation = LocalDateTime.now();
        this.Collaborator = formCreator.getId();
        this.Institution = destinedInstitutionModel.getId();
        this.nameContact = nameOfContact;
        this.lastNameContact = lastNameOfContact;
        this.dateOfEvent = dateOfEvent;
        this.type = typeOfForm.toLowerCase().trim().replace(" ", "");
        this.value = value;
    }
    //endregion
}
