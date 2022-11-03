package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.dtos.InstitutionDTO;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.repositories.InstitutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class InstitutionServiceTest {

    @Mock
    private InstitutionRepository institutionRepository;

    @InjectMocks
    private InstitutionService institutionService;

    private InstitutionModel generateInstitution() {
        return new InstitutionModel (
                "Imip",
                "33455",
                "8199998888",
                "imip@email.com",
                "www.imip.cm.br",
                "hospital imip",
                "Rua do Imip",
                "Recife",
                "PE",
                "12345678"
        );
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(institutionService.save(any(InstitutionModel.class))).thenReturn(generateInstitution());

        // Test whether save returns null
        assertNotNull(institutionService.save(generateInstitution()));

        // Test whether save returns a InstitutionModel
        assertTrue(institutionService.save(generateInstitution()).getClass() == InstitutionModel.class);
    }

    @Test
    void whenFindAllThenReturnAListOfInstitutions() {
        when(institutionService.findAll()).thenReturn(List.of(generateInstitution()));

        assertNotNull(institutionService.findAll());
        assertEquals(1, institutionService.findAll().size());
        assertEquals(InstitutionModel.class, institutionService.findAll().get(0).getClass());
    }

//    @Test
//    void whenFindByNameThenReturnNameOfInstitution() {
//        when(institutionService.findByName(any(String.class))).thenReturn(null);
//
//        assertNotNull(response);
//        assertEquals(InstitutionModel.class, response.getClass());
//        assertEquals(name, response.getName());
//        //esses asserts novamente precisam ser revistos
//    }

    //TODO: Change test name
    @Test
    void other_whenFindByNameThenReturnNameOfInstitution() {
        when(institutionService.findByName(any(String.class))).thenReturn(generateInstitution());

        String name = "Imip";

        assertNotNull(institutionService.findByName(generateInstitution().getName()));
        assertEquals(InstitutionModel.class, institutionService.findByName(generateInstitution().getName()));
        assertEquals(name, generateInstitution().getName());
    }

//    @Test
//    void existsByRegistrationNumber() {
//        String registrationNumber = "number";
//        Mockito.when(institutionRepository.existsByRegistrationNumber(Mockito.anyString())).thenReturn(Boolean.valueOf(registrationNumber));
//        InstitutionModel response = institutionService.findByName(registrationNumber);
//
//        //o response aqui ta nulo
//        assertNotNull(response);
//        assertEquals(InstitutionModel.class, response.getClass());
//    }
//
//    @Test
//    void existsByEmail() {
//       // Mockito.when(institutionRepository.existsByEmail(Mockito.anyString())).thenReturn(Optional.of(institutionModel));
//        String email = "email";
//       // InstitutionModel response = institutionService.existsByEmail(email);
//
//       // assertNotNull(response);
//      //  assertEquals(InstitutionModel.class, response.getClass());
//      //  assertEquals(email, response.getName());//
//    }
//
//    @Test
//    void existsByPhoneNumber() {
//    }
//
//    @Test
//    void existsByUrl() {
//    }
//
//    @Test
//    void existsByName() {
//    }
//
//    @Test
//    void getInstitutionWithMoreSolicitations() {
//        when(institutionRepository.findAll()).thenReturn(List.of(institutionModel));
//        List<InstitutionModel> response = institutionService.getInstitutionWithMoreSolicitations();
//
//        assertNotNull(response);
//
//    }
//
//    @Test
//    void getInstitutionWithLessSolicitations() {
//        when(institutionRepository.findAll()).thenReturn(List.of(institutionModel));
//        List<InstitutionModel> response = institutionService.getInstitutionWithLessSolicitations();
//
//        assertNotNull(response);
//    }
}