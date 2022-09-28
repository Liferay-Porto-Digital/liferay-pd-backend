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

    private Integer annualVolunteerHourGoal = 40;

    private Double annualDonationMoney = 2000.0;
    //endregion

    //region CONSTRUCTORS
    public Collaborator(String name, Boolean isAdministrator) {
        this.name = name;
        this.isAdministrator = isAdministrator;
    }
    //endregion
}
