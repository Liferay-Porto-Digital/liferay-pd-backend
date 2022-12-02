package br.com.liferay.liferaypdbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Class that represents the institutions
 */
@Entity
@Table(name = "institution")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionModel {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    /**
     * Equivalent to CNPJ
     */
    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String url;

    private String description;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Column(nullable = false, length = 8)
    private String zipCode;

    @Column(nullable = false)
    private LocalDate dateOfCreation = LocalDate.now();

    @Column(nullable = false)
    private Integer numberOfActionsReceived = 0;
    //endregion

    //region CONSTRUCTORS
    public InstitutionModel(
            String name,
            String registrationNumber,
            String phoneNumber,
            String email,
            String url,
            String description,
            String street,
            String city,
            String state,
            String zipCode) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.url = url;
        this.description = description;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.dateOfCreation = LocalDate.now();
        this.numberOfActionsReceived = 0;
    }
    //endregion
}
