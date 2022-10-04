package br.com.liferay.liferaypdbackend.models.product;

import br.com.liferay.liferaypdbackend.models.Collaborator;
import br.com.liferay.liferaypdbackend.models.Institution;
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
@Table(name = "Form")
@Data
@NoArgsConstructor
public abstract class Form {
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
    public Form(String typeOfForm, Collaborator formCreator, Institution destinedInstitution, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Double value) {
        this.dateOfCreation = LocalDateTime.now();
        this.Collaborator = formCreator.getId();
        this.Institution = destinedInstitution.getId();
        this.nameContact = nameOfContact;
        this.lastNameContact = lastNameOfContact;
        this.dateOfEvent = dateOfEvent;
        this.type = typeOfForm.toLowerCase().trim().replace(" ", "");
        this.value = value;
    }
    //endregion
}
