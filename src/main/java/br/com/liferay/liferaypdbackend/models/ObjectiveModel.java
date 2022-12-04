package br.com.liferay.liferaypdbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "objective")
@NoArgsConstructor
public class ObjectiveModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Boolean disaster = false;

    @Column(nullable = false)
    private Boolean supplies = false;

    @Column(nullable = false)
    private Boolean health = false;

    @Column(nullable = false)
    private Boolean education = false;

    @Column(nullable = false)
    private Boolean justice = false;

    @Column(nullable = false)
    private Boolean professional = false;

    @Column(nullable = false)
    private String other = "";

    public ObjectiveModel(Boolean disaster, Boolean supplies, Boolean health, Boolean education, Boolean justice, Boolean professional, String other) {
        this.disaster = disaster;
        this.supplies = supplies;
        this.health = health;
        this.education = education;
        this.justice = justice;
        this.professional = professional;
        this.other = other;
    }
}
