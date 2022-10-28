package br.com.liferay.liferaypdbackend.models.concreteProduct;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Class that represents donation forms
 */
@Entity
@NoArgsConstructor
public class DonationFormModel extends FormModel {
    //region CONSTRUCTORS
    public DonationFormModel(String typeOfForm, CollaboratorModel formCreator, InstitutionModel destinedInstitutionModel, String nameOfContact, String lastNameOfContact, LocalDate dateOfEvent, Double value) {
        super(typeOfForm, formCreator, destinedInstitutionModel, nameOfContact, lastNameOfContact, dateOfEvent, value);
    }
    //endregion
}
