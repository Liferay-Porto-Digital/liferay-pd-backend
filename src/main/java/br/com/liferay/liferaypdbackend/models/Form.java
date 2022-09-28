package br.com.liferay.liferaypdbackend.models;

import lombok.Data;

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
public abstract class Form {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID idCollaborator;

    private UUID idInstitution;

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
    //endregion

    //region CONSTRUCTORS
    public Form(Collaborator formCreator, Institution destinedInstitution, String nameOfContact, String lastNameOfContact,LocalDate dateOfEvent) {
        this.dateOfCreation = LocalDateTime.now();
        this.idCollaborator = formCreator.getId();
        this.idInstitution = destinedInstitution.getId();
        this.nameContact = nameOfContact;
        this.lastNameContact = lastNameOfContact;
        this.dateOfEvent = dateOfEvent;
    }

    public Form() {
        this.dateOfCreation = LocalDateTime.now();
    }
    //endregion
}
