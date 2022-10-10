package br.com.liferay.liferaypdbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Class that represents the institutions
 */
@Entity
@Table(name = "InstitutionModel")
@Data
@NoArgsConstructor
public class InstitutionModel {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID idAddress;

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

    @Column(nullable = false)
    private String description;
    //endregion

    //region CONSTRUCTORS
    public InstitutionModel(AddressModel addressModel, String name, String registrationNumber, String phoneNumber, String email, String url, String description) {
        this.idAddress = addressModel.getId();
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.url = url;
        this.description = description;
    }
    //endregion
}
