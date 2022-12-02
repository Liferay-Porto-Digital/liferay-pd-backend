package br.com.liferay.liferaypdbackend.repositories;

import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionModel, UUID> {

    @Query(value = "select institution from InstitutionModel institution where institution.name = :name")
    Optional<InstitutionModel> findByName(@Param(value = "name") String name);

    // TODO: Query institutions with more and less solicitations

    boolean existsByRegistrationNumber(String registrationNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUrl(String url);
    boolean existsByName(String name);

    @Query(value = "select institution from InstitutionModel institution " +
            "inner join FormModel form " +
            "on institution.id = form.institution.id " +
            "group by institution.id " +
            "order by count(institution) desc")
    List<InstitutionModel> getInstitutionListWithMoreSolicitations();

    @Query(value = "select institution from InstitutionModel institution " +
            "inner join FormModel form " +
            "on institution.id = form.institution.id " +
            "group by institution.id " +
            "order by count(institution)")
    List<InstitutionModel> getInstitutionListWithLessSolicitations();

    @Query(value = "select count(form) from FormModel form where form.institution.id = :institutionId")
    Integer getInstitutionNumberOfActionsReceived(@Param(value = "institutionId") UUID institutionId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update InstitutionModel institution " +
            "set institution.numberOfActionsReceived = :numberOfActions " +
            "where institution.id = :institutionId")
    void updateInstitutionNumberOfActionsReceived(
            @Param(value = "numberOfActions") Integer numberOfActions,
            @Param(value = "institutionId") UUID institutionId
    );
}
