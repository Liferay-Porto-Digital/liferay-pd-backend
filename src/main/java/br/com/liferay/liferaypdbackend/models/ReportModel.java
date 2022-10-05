package br.com.liferay.liferaypdbackend.models;

import br.com.liferay.liferaypdbackend.models.concreteProduct.ActivityFormModel;
import br.com.liferay.liferaypdbackend.models.concreteProduct.DonationFormModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Class that represents the reports
 */
@Entity
@Table(name = "ReportModel")
@Data
@NoArgsConstructor
public class ReportModel {
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
    public ReportModel(CollaboratorModel administrator, DonationFormModel donationFormModel, ActivityFormModel activityFormModel, InstitutionModel institutionModel) {
        this.idAdministrator = (administrator.getIsAdministrator()) ? administrator.getId() : null;
        this.idDonation = donationFormModel.getId();
        this.idActivity = activityFormModel.getId();
        this.idInstitution = institutionModel.getId();
    }
    //endregion
}
