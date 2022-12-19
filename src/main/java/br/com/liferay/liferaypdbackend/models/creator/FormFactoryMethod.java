package br.com.liferay.liferaypdbackend.models.creator;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.models.ObjectiveModel;
import br.com.liferay.liferaypdbackend.models.VulnerabilityModel;
import br.com.liferay.liferaypdbackend.models.concreteProduct.ActivityFormModel;
import br.com.liferay.liferaypdbackend.models.concreteProduct.DonationFormModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * FormModel factory
 */
@Component
public class FormFactoryMethod {
    //region METHODS
    public FormModel createForm(
            String typeOfForm,
            CollaboratorModel formCreator,
            InstitutionModel destinedInstitutionModel,
            ObjectiveModel objectiveModel,
            VulnerabilityModel vulnerabilityModel,
            String nameOfContact,
            String lastNameOfContact,
            LocalDate dateOfEvent,
            Double value
    ) {
        FormModel formModel;
        formModel = buildForm(
                typeOfForm,
                formCreator,
                destinedInstitutionModel,
                objectiveModel,
                vulnerabilityModel,
                nameOfContact,
                lastNameOfContact,
                dateOfEvent,
                value
        );
        return formModel;
    }

    private FormModel buildForm(
            String typeOfForm,
            CollaboratorModel formCreator,
            InstitutionModel destinedInstitutionModel,
            ObjectiveModel objectiveModel,
            VulnerabilityModel vulnerabilityModel,
            String nameOfContact,
            String lastNameOfContact,
            LocalDate dateOfEvent,
            Double value
    ) {
        switch (typeOfForm) {
            case "donation":
                return new DonationFormModel(
                        typeOfForm,
                        formCreator,
                        destinedInstitutionModel,
                        objectiveModel,
                        vulnerabilityModel,
                        nameOfContact,
                        lastNameOfContact,
                        dateOfEvent,
                        value
                );
            case "activity":
                return new ActivityFormModel(
                        typeOfForm,
                        formCreator,
                        destinedInstitutionModel,
                        objectiveModel,
                        vulnerabilityModel,
                        nameOfContact,
                        lastNameOfContact,
                        dateOfEvent,
                        value
                );
            default:
                return null;
        }
    }
    //endregion
}
