package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.dtos.FormDTO;
import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.models.concrete_product.ActivityFormModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.CollaboratorRepository;
import br.com.liferay.liferaypdbackend.repositories.FormRepository;
import br.com.liferay.liferaypdbackend.repositories.InstitutionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class FormServiceTest {
    @Mock
    InstitutionRepository institutionRepository;

    @Mock
    CollaboratorRepository collaboratorRepository;

    @Mock
    FormRepository formRepository;

    @InjectMocks
    FormService formService;

    private FormModel generateActivityForm() {
        return new ActivityFormModel();
    }

    private CollaboratorModel generateCollaborator() {
        return new CollaboratorModel (
                "Maria",
                "Auxiliar Administrativo",
                true
        );
    }

    private InstitutionModel generateInstitution() {
        return new InstitutionModel (
                "imip",
                "33455",
                "8199998888",
                "imip@email.com",
                "www.imip.com.br",
                "hospital imip",
                "Rua do Imip",
                "Recife",
                "PE",
                "12345678"
        );
    }

    @Test
    void whenSaveThenReturnSuccess() {
        FormModel activityForm = generateActivityForm();
        when(formRepository.save(any(FormModel.class))).thenReturn(activityForm);

        Assertions.assertNotNull(formService.save(activityForm));
        Assertions.assertSame(ActivityFormModel.class, formService.save(activityForm).getClass());
    }

    @Test
    void whenFindAllThenReturnAListOfFormModel() {
        FormModel activityForm = generateActivityForm();
        when(formRepository.findAll()).thenReturn(Collections.singletonList(activityForm));

        Assertions.assertNotNull(formService.findAll());
        Assertions.assertEquals(1, formService.findAll().size());
        Assertions.assertSame(ActivityFormModel.class, formService.findAll().get(0).getClass());
    }

    @Test
    void whenSaveGetOrSaveInstitution() {
        InstitutionModel institutionModel = generateInstitution();
        FormDTO formDTO = new FormDTO();
        formDTO.setInstitutionName(institutionModel.getName());
        formDTO.setDisasterObjective(true);
        formDTO.setEducationObjective(false);
        formDTO.setJusticeObjective(false);
        formDTO.setHealthObjective(false);
        formDTO.setProfessionalObjective(false);
        formDTO.setSuppliesObjective(false);
        formDTO.setOtherObjective("");
        formDTO.setMonetaryVulnerability(true);
        formDTO.setHealthVulnerability(false);
        formDTO.setHomelessVulnerability(false);
        formDTO.setOtherVulnerability("");
        formDTO.setDateOfEvent("23/05/2025");
        formDTO.setNameContact("Igor");
        formDTO.setLastNameContact("Costa");
        formDTO.setValue(345.8);
        formDTO.setInstitutionCity(institutionModel.getCity());
        formDTO.setInstitutionEmail(institutionModel.getEmail());
        formDTO.setInstitutionState(institutionModel.getState());
        formDTO.setInstitutionStreet(institutionModel.getStreet());
        formDTO.setInstitutionUrl(institutionModel.getUrl());
        formDTO.setInstitutionPhoneNumber(institutionModel.getPhoneNumber());
        formDTO.setInstitutionRegistrationNumber(institutionModel.getRegistrationNumber());
        formDTO.setInstitutionZipCode(institutionModel.getZipCode());

        when(institutionRepository.save(any(InstitutionModel.class))).thenReturn(institutionModel);
        when(institutionRepository.findByName(any(String.class))).thenReturn(Optional.of(institutionModel));

        Assertions.assertEquals(formDTO.getInstitutionName(), formService.saveOrGetInstitution(formDTO).getName());
        Assertions.assertSame(InstitutionModel.class, formService.saveOrGetInstitution(formDTO).getClass());
        Assertions.assertNotNull(formService.saveOrGetInstitution(formDTO));
    }

    @Test
    void getCollaborator() {
        CollaboratorModel collaboratorModel = generateCollaborator();
        when(collaboratorRepository.findAll()).thenReturn(Collections.singletonList(collaboratorModel));

        Assertions.assertSame(CollaboratorModel.class, formService.getCollaborator().getClass());
        Assertions.assertNotNull(formService.getCollaborator());
        Assertions.assertEquals(collaboratorModel, formService.getCollaborator());
    }
}