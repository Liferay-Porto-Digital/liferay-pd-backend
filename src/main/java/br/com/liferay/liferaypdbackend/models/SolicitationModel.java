package br.com.liferay.liferaypdbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Class that represents the solicitations
 */
@Entity
@Table(name = "solicitation")
@Data
@NoArgsConstructor
public class SolicitationModel {
    //region VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @Column(unique = true, nullable = false)
    private CollaboratorModel administrator;

    @Column(nullable = false)
    private String typeOfSolicitation;

    @Column(nullable = false)
    private LocalDateTime solicitationDate;
    //endregion

    //region CONSTRUCTORS
    public SolicitationModel(CollaboratorModel administrator, String typeOfSolicitation) {
        this.solicitationDate = LocalDateTime.now();
        this.administrator = (administrator.getIsAdministrator()) ? administrator : null;
        this.typeOfSolicitation = typeOfSolicitation;
    }
    //endregion
}
