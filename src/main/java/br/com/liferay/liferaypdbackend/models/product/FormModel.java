package br.com.liferay.liferaypdbackend.models.product;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.models.ObjectiveModel;
import br.com.liferay.liferaypdbackend.models.VulnerabilityModel;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Model class for all forms
 */
@Entity
@Table(name = "form")
@Data
public abstract class FormModel {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private CollaboratorModel collaborator;

    @ManyToOne
    private InstitutionModel institution;

    @ManyToOne
    private ObjectiveModel objectives;

    @ManyToOne
    private VulnerabilityModel vulnerabilities;

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
    public FormModel() {
        this.dateOfCreation = LocalDateTime.now();
    }

    public FormModel(String typeOfForm, CollaboratorModel formCreator, InstitutionModel destinedInstitutionModel, ObjectiveModel objectiveModel, VulnerabilityModel vulnerabilityModel, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Double value) {
        this.dateOfCreation = LocalDateTime.now();
        this.collaborator = formCreator;
        this.institution = destinedInstitutionModel;
        this.objectives = objectiveModel;
        this.vulnerabilities = vulnerabilityModel;
        this.nameContact = nameOfContact;
        this.lastNameContact = lastNameOfContact;
        this.dateOfEvent = dateOfEvent;
        this.type = typeOfForm.toLowerCase().trim().replace(" ", "");
        this.value = value;
    }
    //endregion
}
