package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.*;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.SolicitationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class SolicitationServiceTest {
    @Mock
    SolicitationRepository solicitationRepository;

    @InjectMocks
    SolicitationService solicitationService;

    @Before
    public void initServive(){
        MockitoAnnotations.openMocks(this);
    }

    private List<SolicitationModel> generateRepositorySolicitation(){
        List<SolicitationModel> listSolicitations = new ArrayList<>();
        SolicitationModel solicitation1 = new SolicitationModel(generateCollaboratorModel(),"donation");
        SolicitationModel solicitation2 = new SolicitationModel(generateCollaboratorModel(),"activity");
        listSolicitations.add(solicitation1);
        listSolicitations.add(solicitation2);
        return listSolicitations;
    }
    private List<FormModel> generateRepositoryForm(){

        List<FormModel> listForms = new ArrayList<>();
        //criação do formulário 1
        FormModel form1 = new FormModel(
                "donation",
                generateCollaboratorModel(),
                generateInstituitionModel(),
                new ObjectiveModel(),
                new VulnerabilityModel(),
                "Gabriel",
                "Barbosa",
                LocalDate.of(2022, 11, 5),
                100.00) {};
        //criação do formulaŕio 2
        FormModel form2 = new FormModel(
                "activity",
                generateCollaboratorModel(),
                generateInstituitionModel(),
                new ObjectiveModel(),
                new VulnerabilityModel(),
                "Gabriel",
                "Barbosa",
                LocalDate.of(2022, 11, 4),
                20.00) {};
        listForms.add(form1);
        listForms.add(form2);
        return listForms;
    }

    private CollaboratorModel generateCollaboratorModel(){
        return new CollaboratorModel("Gabriel","Auxiliar Administrativo",true);
    }
    private InstitutionModel generateInstituitionModel(){
        return new InstitutionModel (
                "IMIP",
                "123233",
                "(81) 3246-5784",
                "imip@email.com",
                "www.imip.com",
                "saude",
                "Coelhos",
                "Recife",
                "PE",
                "00000-000"
        );
    }

    //test of the findAll
    @Test
    public void whenFindAllThenReturnListSolicitationModel(){
        when(solicitationRepository.findAll()).thenReturn(generateRepositorySolicitation());
        assertNotNull(solicitationService.findAll());
        assertEquals("Gabriel",solicitationService.findAll().get(0).getAdministrator().getName());
    }

    //test of the findAllFormsOrderByNewer
    @Test
    public void whenFindAllThenReturnListFormOrderedNewer(){
        when(solicitationRepository.findAllAndOrderByNewer()).thenReturn(generateRepositoryForm());
        assertNotNull(solicitationService.solicitationRepository.findAllAndOrderByNewer());
        assertEquals(LocalDate.of(2022, 11, 4),solicitationService.solicitationRepository
                .findAllAndOrderByNewer().get(1).getDateOfEvent());
    }
    //test of the findAllFormsAndOrderByOlder
    @Test
    public void whenFindAllThenReturnListFormOrderedOlder(){
        when(solicitationRepository.findAllAndOrderByOlder()).thenReturn(generateRepositoryForm());
        assertNotNull(solicitationService.findAllFormsAndOrderByOlder());
        assertEquals(LocalDate.of(2022, 11, 5),solicitationService
                .findAllFormsAndOrderByOlder().get(0).getDateOfEvent());
    }

    //test of the findAllDonationForms
    @Test
    public void whenFindAllDonationsThenReturnListFormsDonation(){
        when(solicitationRepository.findAllDonation()).thenReturn(generateRepositoryForm());
        assertNotNull(solicitationService.findAllDonationForms());
        assertEquals("donation",solicitationService.findAllDonationForms().get(0).getType());
    }

    //test of the findAllActivityForms
    @Test
    public void whenFindAllActivityThenReturnListFormsActivity(){
        when(solicitationRepository.findAllActivity()).thenReturn(generateRepositoryForm());
        assertNotNull(solicitationService.findAllActivityForms());
        assertEquals("activity",solicitationService.findAllActivityForms().get(1).getType());
    }

    //test of the findAllFormsByInstitutionName
    @Test
    public void whenFindAllInstitutionThenReturnListFormsInstitution(){
        when(solicitationRepository.findAllByInstitutionName(Mockito.anyString())).thenReturn(generateRepositoryForm());
        assertNotNull(solicitationService.findAllFormsByInstitutionName("IMIP"));
        assertEquals("IMIP",solicitationService.findAllFormsByInstitutionName("IMIP").get(0).getInstitution()
                .getName());
    }

    //test of the findAllFormsByCollaboratorName
    @Test
    public void whenFindAllFormsByCollaboratorThenReturnFormCollaborator(){
        when(solicitationRepository.findAllByCollaboratorName(Mockito.anyString()))
                .thenReturn(generateRepositoryForm());
        assertNotNull(solicitationService.findAllFormsByCollaboratorName("Gabriel"));
        assertEquals("Gabriel",solicitationService.findAllFormsByCollaboratorName("Gabriel").get(0)
                .getCollaborator().getName());
    }
    //test of the findCollaboratorByName
    @Test
    public void whenFindCollaboratorThenReturnFormCollaboratorModel(){
        when(solicitationRepository.findCollaboratorByName(Mockito.anyString()))
                .thenReturn(Optional.of(generateCollaboratorModel()));
        assertNotNull(solicitationService.findCollaboratorByName("Gabriel"));
        assertEquals("Gabriel",solicitationService.findCollaboratorByName("Gabriel").getName());
    }
}
