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
     
    private String street;
     
    private String city;

    @Column(length = 2)
    private String state;
     
    private String number;

    @Column(length = 8)
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
