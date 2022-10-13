package br.com.liferay.liferaypdbackend.repositories;

import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IInstitutionRepository extends JpaRepository<InstitutionModel, UUID> {

    boolean existsByRegistrationNumber(String registrationNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUrl(String url);
    boolean existsByName(String name);
}
