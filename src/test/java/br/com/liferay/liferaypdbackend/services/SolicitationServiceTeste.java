package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.models.SolicitationModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.SolicitationRepository;
import br.com.liferay.liferaypdbackend.services.SolicitationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class SolicitationServiceTeste {
    @Mock
    SolicitationRepository solicitationRepository;

    @InjectMocks
    SolicitationService solicitationService;

    @Before
    public void initServive(){
        SolicitationService solicitationService = new SolicitationService(solicitationRepository);
        MockitoAnnotations.openMocks(this);
    }

    private List<SolicitationModel> generateRepositorySolicitation(){
        List<SolicitationModel> listSolicitations = new ArrayList<>();
        SolicitationModel solicitation1 = new SolicitationModel(generateCollaboratorModel(),"donation");
        SolicitationModel solicitation2 = new SolicitationModel(generateCollaboratorModel(),"activity");
        listSolicitations.add(solicitation1);
        listSolicitations.add(solicitation2);
        return listSolicitations;
    };
    private List<FormModel> generateRepositoryForm(){

        List<FormModel> listForms = new ArrayList<>();
        //criação do formulário 1
        FormModel form1 = new FormModel(
                "donation",
                generateCollaboratorModel(),
                generateInstituitionModel(),
                "Gabriel",
                "Barbosa",
                LocalDate.of(2022, 11, 5),
                100.00) {};
        //criação do formulaŕio 2
        FormModel form2 = new FormModel(
                "activity",
                generateCollaboratorModel(),
                generateInstituitionModel(),
                "Gabriel",
                "Barbosa",
                LocalDate.of(2022, 11, 4),
                20.00) {};
        listForms.add(form1);
        listForms.add(form2);
        return listForms;
    };

    private SolicitationModel generateSolicitationModel(){
        SolicitationModel solicitation = new SolicitationModel(generateCollaboratorModel(),"donation");
        return solicitation;
    };

    private CollaboratorModel generateCollaboratorModel(){
        CollaboratorModel collaborator = new CollaboratorModel("Gabriel","Auxiliar Administrativo",true);
        return collaborator;
    };
    private InstitutionModel generateInstituitionModel(){
        InstitutionModel institution = new InstitutionModel();
        return institution;
    };

    //test of the findAll
    @Test
    public void whenFindAllthenReturnListSolicitationModel(){
        when(solicitationRepository.findAll()).thenReturn(generateRepositorySolicitation());
        assertNotNull(solicitationService.findAll());
        assertEquals("Gabriel",generateRepositorySolicitation().get(0).getAdministrator().getName());
    }

    //test of the findAllFormsOrderByNewer
    @Test
    public void whenFindAllthenReturnListFormOrdered(){
        when(solicitationRepository.findAllAndOrderByNewer()).thenReturn(generateRepositoryForm());
        assertNotNull(solicitationService.findAllFormsAndOrderByOlder());
        assertEquals("Gabriel",generateRepositoryForm().get(0).getCollaborator().getName());
    }
    //test of the findAllFormsAndOrderByOlder

    //test of the findAllDonationForms

    //test of the findAllActivityForms

    //test of the findAllFormsByInstitutionName

    //test of the findAllFormsByCollaboratorName

    //test of the findCollaboratorByName

}
