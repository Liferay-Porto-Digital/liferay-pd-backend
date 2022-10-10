package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.repositories.IInstitutionRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void saveInstitution(InstitutionModel institutionModel) {
        institutionRepository.save(institutionModel);
    }

    /**
     * Delete/Drop institution in the database
     * @param institutionModel
     */
    @Transactional
    @Modifying
    public void deleteInstitution(InstitutionModel institutionModel) {
        institutionRepository.delete(institutionModel);
    }

    /**
     * Get all institutions from the database
     * @return List
     */
    public List<InstitutionModel> getAllInstitutions() {
        return institutionRepository.findAll();
    }
    //endregion
}