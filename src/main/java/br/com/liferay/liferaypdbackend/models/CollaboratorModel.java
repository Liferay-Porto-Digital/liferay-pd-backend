package br.com.liferay.liferaypdbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Model class for the users
 */
@Entity
@Table(name = "collaborator")
@Data
@NoArgsConstructor
public class CollaboratorModel {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isAdministrator;

    @Column(nullable = false)
    private String jobRole;

    @Column(nullable = false)
    private Integer annualVolunteerHourGoal = 40;

    @Column(nullable = false)
    private Double annualDonationMoney = 1000.0;
    //endregion

    //region CONSTRUCTORS
    public CollaboratorModel(String name, String jobRole, Boolean isAdministrator) {
        this.name = name;
        this.jobRole = jobRole;
        this.isAdministrator = isAdministrator;
    }
    //endregion
}
