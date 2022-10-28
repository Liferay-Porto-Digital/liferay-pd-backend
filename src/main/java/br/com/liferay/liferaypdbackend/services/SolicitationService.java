package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.SolicitationModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.SolicitationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitationService {
    //region INJECTIONS
    final SolicitationRepository solicitationRepository;

    public SolicitationService(SolicitationRepository solicitationRepository) {
        this.solicitationRepository = solicitationRepository;
    }
    //endregion

    //region METHODS
    public void save(SolicitationModel solicitationModel) {
        solicitationRepository.save(solicitationModel);
    }

    public List<SolicitationModel> findAll() {
        if (!solicitationRepository.findAll().isEmpty()) {
            return solicitationRepository.findAll();
        }
        return null;
    }

    public List<FormModel> findAllFormsOrderByNewer() {
        return solicitationRepository.findAllAndOrderByNewer();
    }

    public List<FormModel> findAllFormsAndOrderByOlder() {
        return solicitationRepository.findAllAndOrderByOlder();
    }

    public List<FormModel> findAllDonationForms() {
        return solicitationRepository.findAllDonation();
    }

    public List<FormModel> findAllActivityForms() {
        return solicitationRepository.findAllActivity();
    }

    public List<FormModel> findAllFormsByInstitutionName(String name) {
        return solicitationRepository.findAllByInstitutionName(name);
    }

    public List<FormModel> findAllFormsByCollaboratorName(String name) {
        return solicitationRepository.findAllByCollaboratorName(name);
    }

    public CollaboratorModel findCollaboratorByName(String name) {
        if (!solicitationRepository.findCollaboratorByName(name).isPresent()) {
            return null;
        }
        return solicitationRepository.findCollaboratorByName(name).get();
    }
    //endregion
}
