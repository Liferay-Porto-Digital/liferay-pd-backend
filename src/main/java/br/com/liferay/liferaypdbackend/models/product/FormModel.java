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

    @Column(nullable = false, unique = true)
    private UUID Collaborator;

    @Column(nullable = false, unique = true)
    private UUID Institution;

//    TODO: Analyse better implementation of the following logic (Maybe ENUM?)
//    @ElementCollection
//    private List<String> objective;
//
//    @ElementCollection
//    private List<String> vulnerability;

    @Column(nullable = false)
    private LocalDateTime dateOfCreation;

    @Column(nullable = false)
    private LocalDate dateOfEvent;

    @Column(nullable = false)
    private String nameContact;

    @Column(nullable = false)
    private String lastNameContact;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double value;
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
