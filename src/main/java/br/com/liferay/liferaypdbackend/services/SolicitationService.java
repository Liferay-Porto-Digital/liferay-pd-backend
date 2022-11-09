package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.SolicitationModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.SolicitationRepository;
import br.com.liferay.liferaypdbackend.services.utils.ConsoleLogUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
        try {
            if (solicitationRepository.findAllByInstitutionName(name) != null) {
                return solicitationRepository.findAllByInstitutionName(name);
            }
        } catch (Exception e) {
            ConsoleLogUtil.log.info(Arrays.toString(e.getStackTrace()));
        }
        return new ArrayList<>();
    }

    public List<FormModel> findAllFormsByCollaboratorName(String name) {
        try {
            if (solicitationRepository.findAllByCollaboratorName(name) != null) {
                return solicitationRepository.findAllByCollaboratorName(name);
            }
        } catch (Exception e) {
            ConsoleLogUtil.log.info(Arrays.toString(e.getStackTrace()));
        }
        return new ArrayList<>();
    }

    public CollaboratorModel findCollaboratorByName(String name) {
        if (!solicitationRepository.findCollaboratorByName(name).isPresent()) {
            return null;
        }
        return solicitationRepository.findCollaboratorByName(name).get();
    }
    //endregion
}
