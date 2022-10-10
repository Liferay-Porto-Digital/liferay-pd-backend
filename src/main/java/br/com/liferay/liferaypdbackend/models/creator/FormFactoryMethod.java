package br.com.liferay.liferaypdbackend.models.creator;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.models.concreteProduct.ActivityFormModel;
import br.com.liferay.liferaypdbackend.models.concreteProduct.DonationFormModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;

import java.time.LocalDate;

/**
 * FormModel factory
 */
public class FormFactoryMethod {
    //region METHODS
    public FormModel createForm(String typeOfForm, CollaboratorModel formCreator, InstitutionModel destinedInstitutionModel, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Object value) {
        FormModel formModel;
        formModel = buildForm(typeOfForm, formCreator, destinedInstitutionModel, nameOfContact, lastNameOfContact, dateOfEvent, value);
        return formModel;
    }

    private FormModel buildForm(String typeOfForm, CollaboratorModel formCreator, InstitutionModel destinedInstitutionModel, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Object value) {
        switch (typeOfForm) {
            case "donation":
                return new DonationFormModel(typeOfForm, formCreator, destinedInstitutionModel, nameOfContact, lastNameOfContact, dateOfEvent, (Double)value);
            case "activity":
                return new ActivityFormModel(typeOfForm, formCreator, destinedInstitutionModel, nameOfContact, lastNameOfContact, dateOfEvent, (Integer)value);
            default:
                //TODO: See viability of throwing an exception here
                return null;
        }
    }
    //endregion
}
