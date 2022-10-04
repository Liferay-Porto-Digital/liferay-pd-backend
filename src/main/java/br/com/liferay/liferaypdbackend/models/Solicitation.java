package br.com.liferay.liferaypdbackend.models;

import br.com.liferay.liferaypdbackend.models.concreteProduct.ActivityForm;
import br.com.liferay.liferaypdbackend.models.concreteProduct.DonationForm;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Class that represents the solicitations
 */
@Entity
@Table(name = "Solicitation")
@Data
@NoArgsConstructor
public class Solicitation {
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
    public Solicitation(Collaborator administrator, DonationForm donationForm, ActivityForm activityForm, Institution institution) {
        this.idAdministrator = (administrator.getIsAdministrator()) ? administrator.getId() : null;
        this.idDonation = donationForm.getId();
        this.idActivity = activityForm.getId();
        this.idInstitution = institution.getId();
    }
    //endregion
}
