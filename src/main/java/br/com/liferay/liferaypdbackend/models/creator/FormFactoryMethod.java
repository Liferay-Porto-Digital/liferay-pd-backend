package br.com.liferay.liferaypdbackend.models.creator;

import br.com.liferay.liferaypdbackend.models.Collaborator;
import br.com.liferay.liferaypdbackend.models.Institution;
import br.com.liferay.liferaypdbackend.models.concreteProduct.ActivityForm;
import br.com.liferay.liferaypdbackend.models.concreteProduct.DonationForm;
import br.com.liferay.liferaypdbackend.models.product.Form;

import java.time.LocalDate;

/**
 * Form factory
 */
public class FormFactoryMethod {
    //region METHODS
    public Form createForm(String typeOfForm, Collaborator formCreator, Institution destinedInstitution, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Double value) {
        Form form;
        form = buildForm(typeOfForm, formCreator, destinedInstitution, nameOfContact, lastNameOfContact, dateOfEvent, value);
        return form;
    }

    private Form buildForm(String typeOfForm, Collaborator formCreator, Institution destinedInstitution, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Double value) {
        switch (typeOfForm) {
            case "donation":
                return new DonationForm(typeOfForm, formCreator, destinedInstitution, nameOfContact, lastNameOfContact, dateOfEvent, value);
            case "activity":
                return new ActivityForm(typeOfForm, formCreator, destinedInstitution, nameOfContact, lastNameOfContact, dateOfEvent, value);
            default:
                //TODO: See viability of throwing an exception here
                return null;
        }
    }
    //endregion
}
