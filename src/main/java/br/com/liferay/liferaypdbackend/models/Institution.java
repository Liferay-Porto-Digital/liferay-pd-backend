package br.com.liferay.liferaypdbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Class that represents the institutions
 */
@Entity
@Table(name = "Institution")
@Data
@NoArgsConstructor
public class Institution {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID idAddress;

    private String name;

    /**
     * Equivalent to CNPJ
     */
    private String registrationNumber;

    private String phoneNumber;

    private String email;

    private String url;

    private String description;
    //endregion

    //region CONSTRUCTORS
    public Institution(Address address, String name, String registrationNumber, String phoneNumber, String email, String url, String description) {
        this.idAddress = address.getId();
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.url = url;
        this.description = description;
    }
    //endregion
}
