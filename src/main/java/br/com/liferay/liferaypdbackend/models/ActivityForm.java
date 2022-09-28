package br.com.liferay.liferaypdbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Class that represents activity forms
 */
@Entity
@Data
@NoArgsConstructor
public class ActivityForm extends Form {
    //region VARIABLES
    private Integer hoursVolunteered;
    //endregion

    //region CONSTRUCTORS
    public ActivityForm(Collaborator formCreator, Institution destinedInstitution, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Integer hoursVolunteered) {
        super(formCreator, destinedInstitution, nameOfContact, lastNameOfContact, dateOfEvent);
        this.hoursVolunteered = hoursVolunteered;
    }
    //endregion
}
