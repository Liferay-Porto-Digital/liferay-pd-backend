package br.com.liferay.liferaypdbackend.models;

import br.com.liferay.liferaypdbackend.models.concreteProduct.ActivityFormModelModel;
import br.com.liferay.liferaypdbackend.models.concreteProduct.DonationFormModelModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Class that represents the solicitations
 */
@Entity
@Table(name = "SolicitationModel")
@Data
@NoArgsConstructor
public class SolicitationModel {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID idAdministrator;

    private UUID idDonation;

    private UUID idActivity;

    private UUID idInstitution;
    //endregion

    //region CONSTRUCTORS
    public SolicitationModel(CollaboratorModel administrator, DonationFormModelModel donationFormModel, ActivityFormModelModel activityFormModel, InstitutionModel institutionModel) {
        this.idAdministrator = (administrator.getIsAdministrator()) ? administrator.getId() : null;
        this.idDonation = donationFormModel.getId();
        this.idActivity = activityFormModel.getId();
        this.idInstitution = institutionModel.getId();
    }
    //endregion
}
