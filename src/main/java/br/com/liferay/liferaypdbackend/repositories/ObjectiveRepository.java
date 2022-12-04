package br.com.liferay.liferaypdbackend.repositories;

import br.com.liferay.liferaypdbackend.models.ObjectiveModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ObjectiveRepository extends JpaRepository<ObjectiveModel, UUID> {
    Boolean existsByOtherAndDisasterAndSuppliesAndHealthAndEducationAndJusticeAndProfessional(
            String other,
            Boolean disaster,
            Boolean supplies,
            Boolean health,
            Boolean education,
            Boolean justice,
            Boolean professional
    );

    @Query(value = "select obj from ObjectiveModel obj " +
            "where obj.other = :other " +
            "and obj.disaster = :disaster " +
            "and obj.supplies = :supplies " +
            "and obj.health = :health " +
            "and obj.education = :education " +
            "and obj.justice = :justice " +
            "and obj.professional = :professional")
    Optional<ObjectiveModel> findByAll(
            @Param(value = "other") String other,
            @Param(value = "disaster") Boolean disaster,
            @Param(value = "supplies") Boolean supplies,
            @Param(value = "health") Boolean health,
            @Param(value = "education") Boolean education,
            @Param(value = "justice") Boolean justice,
            @Param(value = "professional") Boolean professional
    );
}
