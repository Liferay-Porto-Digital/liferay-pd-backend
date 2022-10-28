package br.com.liferay.liferaypdbackend.repositories;

import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
