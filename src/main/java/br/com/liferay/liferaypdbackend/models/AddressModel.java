package br.com.liferay.liferaypdbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Class that represents institution's address
 */
@Entity
@Table(name = "AddressModel")
@Data
@NoArgsConstructor
public class AddressModel {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false, length = 8)
    private String zipCode;
    //endregion

    //region CONSTRUCTORS
    public AddressModel(String street, String city, String state, String number, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.number = number;
        this.zipCode = zipCode;
    }
    //endregion
}
