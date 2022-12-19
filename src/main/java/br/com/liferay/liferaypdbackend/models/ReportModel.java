package br.com.liferay.liferaypdbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Class that represents the reports
 */
@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
public class ReportModel {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer numberOfDonations = 0;

    private Double amountDonated = 0.0;

    private Double donationsPerInstitution = 0.0;

    private Integer numberOfActivities = 0;

    private Double amountDoneActivity = 0.0;

    private Double activityPerInstitution = 0.0;

    private LocalDateTime reportUpdateDate;
    //endregion

    //region CONSTRUCTORS
    public ReportModel(Integer numberOfDonations, Double amountDonated, Double donationsPerInstitution, Integer numberOfActivities, Double amountDoneActivity, Double activityPerInstitution) {
        this.reportUpdateDate = LocalDateTime.now();
        this.numberOfDonations = numberOfDonations;
        this.amountDonated = amountDonated;
        this.donationsPerInstitution = donationsPerInstitution;
        this.numberOfActivities = numberOfActivities;
        this.amountDoneActivity = amountDoneActivity;
        this.activityPerInstitution = activityPerInstitution;
    }
    //endregion
}
