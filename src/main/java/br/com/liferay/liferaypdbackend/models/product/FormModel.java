package br.com.liferay.liferaypdbackend.models.product;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

    @ElementCollection
    private List<String> objective;

    @ElementCollection
    private List<String> vulnerability;

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

    public FormModel(String typeOfForm, CollaboratorModel formCreator, InstitutionModel destinedInstitutionModel, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Double value) {
        this.dateOfCreation = LocalDateTime.now();
        this.collaborator = formCreator;
        this.institution = destinedInstitutionModel;
        this.nameContact = nameOfContact;
        this.lastNameContact = lastNameOfContact;
        this.dateOfEvent = dateOfEvent;
        this.type = typeOfForm.toLowerCase().trim().replace(" ", "");
        this.value = value;
    }
    //endregion
}
