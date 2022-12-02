package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.dtos.FormDTO;
import br.com.liferay.liferaypdbackend.dtos.InstitutionDTO;
import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.CollaboratorRepository;
import br.com.liferay.liferaypdbackend.repositories.FormRepository;
import br.com.liferay.liferaypdbackend.repositories.InstitutionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {
    //region INJECTIONS
    final FormRepository formRepository;
    final InstitutionRepository institutionRepository;
    final CollaboratorRepository collaboratorRepository;

    public FormService(FormRepository formRepository, InstitutionRepository institutionRepository,
                       CollaboratorRepository collaboratorRepository) {
        this.formRepository = formRepository;
        this.institutionRepository = institutionRepository;
        this.collaboratorRepository = collaboratorRepository;
    }
    //endregion

    //region METHODS
    public FormModel save(FormModel formModel) {
        return formRepository.save(formModel);
    }

    public List<FormModel> findAll() {
        if (!formRepository.findAll().isEmpty()) {
            return formRepository.findAll();
        }
        return null;
    }

    public InstitutionModel saveOrGetInstitution(FormDTO formDTO) {

        InstitutionDTO institutionDTO = new InstitutionDTO (
                formDTO.getInstitutionName().trim().toUpperCase(),
                formDTO.getInstitutionRegistrationNumber(),
                formDTO.getInstitutionPhoneNumber(),
                formDTO.getInstitutionEmail(),
                formDTO.getInstitutionUrl(),
                " ",
                formDTO.getInstitutionStreet(),
                formDTO.getInstitutionCity(),
                formDTO.getInstitutionState(),
                formDTO.getInstitutionZipCode()
        );
        InstitutionModel institutionModel = new InstitutionModel();
        BeanUtils.copyProperties(institutionDTO, institutionModel);
        if (!institutionRepository.existsByName(institutionModel.getName()) &&
                !institutionRepository.findByName(institutionModel.getName()).isPresent()) {
            return institutionRepository.save(institutionModel);
        }
        return institutionRepository.findByName(institutionModel.getName()).get();
    }

    public CollaboratorModel getCollaborator() {
        if (!collaboratorRepository.findAll().isEmpty()) {
            return collaboratorRepository.findAll().get(0);
        }
        return null;
    }

    public FormModel updateInstitutionNumberOfActionsReceived(FormModel formModel) {
        Integer numberOfActionsReceived = institutionRepository.getInstitutionNumberOfActionsReceived(
                formModel.getInstitution().getId()
        );

        institutionRepository.updateInstitutionNumberOfActionsReceived(
                numberOfActionsReceived,
                formModel.getInstitution().getId()
        );

        formModel.getInstitution().setNumberOfActionsReceived(numberOfActionsReceived);

        return formModel;
    }
    //endregion
}
