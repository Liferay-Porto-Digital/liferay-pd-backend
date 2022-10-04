package br.com.liferay.liferaypdbackend.models.concreteProduct;

import br.com.liferay.liferaypdbackend.models.Collaborator;
import br.com.liferay.liferaypdbackend.models.Institution;
import br.com.liferay.liferaypdbackend.models.product.Form;

import java.time.LocalDate;

/**
 * Class that represents donation forms
 */
public class DonationForm extends Form {
    //region CONSTRUCTORS
    public DonationForm(String typeOfForm, Collaborator formCreator, Institution destinedInstitution, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Double value) {
        super(typeOfForm, formCreator, destinedInstitution, nameOfContact, lastNameOfContact, dateOfEvent, value);
    }
    //endregion
}
