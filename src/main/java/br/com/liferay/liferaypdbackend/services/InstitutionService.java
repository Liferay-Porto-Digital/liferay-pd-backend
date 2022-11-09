package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.repositories.InstitutionRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionService {

    //region INJECTIONS
    final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }
    //endregion

    //region METHODS

    /**
     * Save institution in database
     * @param institutionModel
     */
    @Transactional
    @Modifying
    public InstitutionModel save(InstitutionModel institutionModel) {
        return institutionRepository.save(institutionModel);
    }

    /**
     * Get all institutions from the database
     * @return List
     */
    public List<InstitutionModel> findAll() {
        if (!institutionRepository.findAll().isEmpty()) {
            return institutionRepository.findAll();
        }
        return null;
    }

    public Optional<InstitutionModel> findByName(String name) {
        if (institutionRepository.findByName(name).isPresent()) {
            return institutionRepository.findByName(name);
        }
        return Optional.empty();
    }

    public boolean existsByRegistrationNumber(String registrationNumber) {
        return institutionRepository.existsByRegistrationNumber(registrationNumber);
    }

    public boolean existsByEmail(String email) {
        return institutionRepository.existsByEmail(email);
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return institutionRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean existsByUrl(String url) {
        return institutionRepository.existsByUrl(url);
    }

    public boolean existsByName(String name) {
        return institutionRepository.existsByName(name);
    }

    public List<InstitutionModel> getInstitutionWithMoreSolicitations() {
        return institutionRepository.getInstitutionListWithMoreSolicitations();
    }

    public List<InstitutionModel> getInstitutionWithLessSolicitations() {
        return institutionRepository.getInstitutionListWithLessSolicitations();
    }
    //endregion
}
