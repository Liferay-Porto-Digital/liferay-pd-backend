package br.com.liferay.liferaypdbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Model class for the users
 */
@Entity
@Table(name = "Collaborator")
@Data
@NoArgsConstructor
public class Collaborator {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private Boolean isAdministrator;

    private String jobRole;

    private Integer annualVolunteerHourGoal = 40;

    private Double annualDonationMoney = 1000.0;
    //endregion

    //region CONSTRUCTORS
    public Collaborator(String name, String jobRole, Boolean isAdministrator) {
        this.name = name;
        this.jobRole = jobRole;
        this.isAdministrator = isAdministrator;
    }
    //endregion
}
