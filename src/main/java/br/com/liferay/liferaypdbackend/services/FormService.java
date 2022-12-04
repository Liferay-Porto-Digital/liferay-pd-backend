package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.dtos.FormDTO;
import br.com.liferay.liferaypdbackend.dtos.InstitutionDTO;
import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.models.ObjectiveModel;
import br.com.liferay.liferaypdbackend.models.VulnerabilityModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormService {
    //region INJECTIONS
    @Autowired
    ObjectiveRepository objectiveRepository;

    @Autowired
    VulnerabilityRepository vulnerabilityRepository;

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

    public VulnerabilityModel saveOrGetVulnerability(FormDTO formDTO) {
        VulnerabilityModel vulnerabilityModel = new VulnerabilityModel(
                formDTO.getMonetaryVulnerability(),
                formDTO.getHealthVulnerability(),
                formDTO.getHomelessVulnerability(),
                formDTO.getOtherVulnerability().trim().toUpperCase()
        );

        Boolean vulnerabilityExists = vulnerabilityRepository.existsByOtherAndMonetaryAndHealthAndHomeless(
                formDTO.getOtherVulnerability(),
                formDTO.getMonetaryVulnerability(),
                formDTO.getHealthVulnerability(),
                formDTO.getHomelessVulnerability()
        );

        Optional<VulnerabilityModel> optional = vulnerabilityRepository.findByAll(
            vulnerabilityModel.getOther(),
            vulnerabilityModel.getMonetary(),
            vulnerabilityModel.getHealth(),
            vulnerabilityModel.getHomeless()
        );

        if (vulnerabilityExists && optional.isPresent()) {
            return optional.get();
        }

        return vulnerabilityRepository.save(vulnerabilityModel);
    }

    public ObjectiveModel saveOrGetObjective(FormDTO formDTO) {
        ObjectiveModel objectiveModel =  new ObjectiveModel(
                formDTO.getDisasterObjective(),
                formDTO.getSuppliesObjective(),
                formDTO.getHealthObjective(),
                formDTO.getEducationObjective(),
                formDTO.getJusticeObjective(),
                formDTO.getProfessionalObjective(),
                formDTO.getOtherObjective().trim().toUpperCase()
        );

        Boolean objectExists = objectiveRepository.existsByOtherAndDisasterAndSuppliesAndHealthAndEducationAndJusticeAndProfessional(
                objectiveModel.getOther(),
                objectiveModel.getDisaster(),
                objectiveModel.getSupplies(),
                objectiveModel.getHealth(),
                objectiveModel.getEducation(),
                objectiveModel.getJustice(),
                objectiveModel.getProfessional()
        );

        Optional<ObjectiveModel> optional = objectiveRepository.findByAll(
                objectiveModel.getOther(),
                objectiveModel.getDisaster(),
                objectiveModel.getSupplies(),
                objectiveModel.getHealth(),
                objectiveModel.getEducation(),
                objectiveModel.getJustice(),
                objectiveModel.getProfessional()
        );

        if (objectExists && optional.isPresent()) {
            return optional.get();
        }

        return objectiveRepository.save(objectiveModel);

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
