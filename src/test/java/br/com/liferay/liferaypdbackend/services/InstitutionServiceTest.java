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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
        when(institutionService.save(any(InstitutionModel.class))).thenReturn(generateInstitution());

        // Test whether save returns null
        assertNotNull(institutionService.save(generateInstitution()));

        // Test whether save returns a InstitutionModel
        assertTrue(institutionService.save(generateInstitution()).getClass() == InstitutionModel.class);
    }

    @Test
    void whenFindAllThenReturnAListOfInstitutions() {
        when(institutionService.findAll()).thenReturn(Arrays.asList(generateInstitution()));

        assertNotNull(institutionService.findAll());
        assertEquals(1, institutionService.findAll().size());
        assertEquals(InstitutionModel.class, institutionService.findAll().get(0).getClass());
    }

    //TODO: Change test name
//    @Test
//    void whenFindByNameThenReturnNameOfInstitution() {
//        when(institutionService.findByName()).thenReturn(null);
//
//        assertNotNull(response);
//        assertEquals(InstitutionModel.class, response.getClass());
//        assertEquals(name, response.getName());
//        //esses asserts novamente precisam ser revistos
//    }
//
//
//    @Test
//    void other_whenFindByNameThenReturnNameOfInstitution() {
//        when(institutionService.findByName(any(String.class))).thenReturn(generateInstitution());
//
//        String name = "Imip";
//
//        assertNotNull(institutionService.findByName(generateInstitution().getName()));
//        assertEquals(InstitutionModel.class, institutionService.findByName(generateInstitution().getName()));
//        assertEquals(name, generateInstitution().getName());
//    }

    @Test
    void existsByRegistrationNumber() {
        when(institutionRepository.existsByRegistrationNumber(any(String.class))).thenReturn(!generateInstitution().getRegistrationNumber().isEmpty());

        assertNotNull(generateInstitution());
        assertEquals(InstitutionModel.class, generateInstitution().getClass());
        assertEquals("33455", generateInstitution().getRegistrationNumber());
    }

    @Test
    void existsByEmail() {
        when(institutionRepository.existsByEmail(any(String.class))).thenReturn(!generateInstitution().getEmail().isEmpty());

        assertNotNull(generateInstitution());
        assertEquals(InstitutionModel.class, generateInstitution().getClass());
        assertEquals("imip@email.com", generateInstitution().getEmail());//
    }

    @Test
    void existsByPhoneNumber() {
     when(institutionRepository.existsByPhoneNumber(any(String.class))).thenReturn(!generateInstitution().getPhoneNumber().isEmpty());
     
     assertNotNull(generateInstitution());
     assertEquals(InstitutionModel.class, generateInstitution().getClass());
     assertEquals("8199998888", generateInstitution().getPhoneNumber());
    }

    @Test
    void existsByUrl() {
        when(institutionRepository.existsByUrl(any(String.class))).thenReturn(!generateInstitution().getUrl().isEmpty());

        assertNotNull(generateInstitution());
        assertEquals(InstitutionModel.class, generateInstitution().getClass());
        assertEquals("www.imip.com.br", generateInstitution().getUrl());
    }

   @Test
    void existsByName() {
       when(institutionRepository.existsByName(any(String.class))).thenReturn(!generateInstitution().getName().isEmpty());

       assertNotNull(generateInstitution());
       assertEquals(InstitutionModel.class, generateInstitution().getClass());
       assertEquals("Imip", generateInstitution().getName());
   }

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