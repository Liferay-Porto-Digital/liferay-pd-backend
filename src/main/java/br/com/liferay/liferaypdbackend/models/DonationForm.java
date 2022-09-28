package br.com.liferay.liferaypdbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Class that represents donation forms
 */
@Entity
@Data
@NoArgsConstructor
public class DonationForm extends Form {
    //region VARIABLES
    private Double amountDonated;
    //endregion

    //region CONSTRUCTORS
    public DonationForm(Collaborator formCreator, Institution destinedInstitution, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Double amountDonated) {
        super(formCreator, destinedInstitution, nameOfContact, lastNameOfContact, dateOfEvent);
        this.amountDonated = amountDonated;
    }
    //endregion
}
