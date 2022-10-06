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

    @Column(nullable = false, unique = true)
    private UUID idAdministrator;

    @Column(nullable = false, unique = true)
    private UUID idDonation;

    @Column(nullable = false, unique = true)
    private UUID idActivity;

    @Column(nullable = false, unique = true)
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
