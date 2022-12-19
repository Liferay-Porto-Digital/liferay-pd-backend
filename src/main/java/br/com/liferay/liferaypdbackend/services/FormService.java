package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.dtos.FormDTO;
import br.com.liferay.liferaypdbackend.dtos.InstitutionDTO;
import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.models.ObjectiveModel;
import br.com.liferay.liferaypdbackend.models.VulnerabilityModel;
import br.com.liferay.liferaypdbackend.models.creator.FormFactoryMethod;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FormService {
    //region INJECTIONS
    @Autowired
    ObjectiveRepository objectiveRepository;
    @Autowired
    VulnerabilityRepository vulnerabilityRepository;
    @Autowired
    FormRepository formRepository;
    @Autowired
    InstitutionRepository institutionRepository;
    @Autowired
    CollaboratorRepository collaboratorRepository;
    @Autowired
    FormFactoryMethod formFactoryMethod;
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
        return vulnerabilityRepository.save(new VulnerabilityModel(
                formDTO.getMonetaryVulnerability(),
                formDTO.getHealthVulnerability(),
                formDTO.getHomelessVulnerability(),
                formDTO.getOtherVulnerability().trim().toUpperCase()
        ));
    }

    public ObjectiveModel saveOrGetObjective(FormDTO formDTO) {
        return objectiveRepository.save(new ObjectiveModel(
                formDTO.getDisasterObjective(),
                formDTO.getSuppliesObjective(),
                formDTO.getHealthObjective(),
                formDTO.getEducationObjective(),
                formDTO.getJusticeObjective(),
                formDTO.getProfessionalObjective(),
                formDTO.getOtherObjective().trim().toUpperCase()
        ));
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

    public FormModel createFormModel(String typeOfForm, FormDTO formDTO) {
        return formFactoryMethod.createForm (
                typeOfForm,
                getCollaborator(),
                saveOrGetInstitution(formDTO),
                saveOrGetObjective(formDTO),
                saveOrGetVulnerability(formDTO),
                formDTO.getNameContact(),
                formDTO.getLastNameContact(),
                LocalDate.parse(formDTO.getDateOfEvent(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                formDTO.getValue()
        );
    }
    //endregion
}
