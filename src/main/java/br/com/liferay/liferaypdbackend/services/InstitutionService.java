package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.repositories.IInstitutionRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstitutionService {

    //region INJECTIONS
    final IInstitutionRepository institutionRepository;

    public InstitutionService(IInstitutionRepository iInstitutionRepository) {
        this.institutionRepository = iInstitutionRepository;
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
     * Delete/Drop institution in the database
     * @param institutionModel
     */
    @Transactional
    @Modifying
    public void delete(InstitutionModel institutionModel) {
        institutionRepository.delete(institutionModel);
    }

    /**
     * Get all institutions from the database
     * @return List
     */
    public List<InstitutionModel> findAll() {
        return institutionRepository.findAll();
    }

    public Optional<InstitutionModel> findById(UUID id) {
        return institutionRepository.findById(id);
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
    //endregion
}
