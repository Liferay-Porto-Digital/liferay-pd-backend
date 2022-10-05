package br.com.liferay.liferaypdbackend.models.concreteProduct;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;

import java.time.LocalDate;

/**
 * Class that represents activity forms
 */
public class ActivityFormModelModel extends FormModel {
    //region CONSTRUCTORS
    public ActivityFormModelModel(String typeOfForm, CollaboratorModel formCreator, InstitutionModel destinedInstitutionModel, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Double value) {
        super(typeOfForm, formCreator, destinedInstitutionModel, nameOfContact, lastNameOfContact, dateOfEvent, value);
    }
    //endregion
}
