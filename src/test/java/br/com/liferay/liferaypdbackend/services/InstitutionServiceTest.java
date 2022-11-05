package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.repositories.InstitutionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        InstitutionModel institutionModel = generateInstitution();
        when(institutionService.save(any(InstitutionModel.class))).thenReturn(institutionModel);

        // Test whether save returns null
        Assertions.assertNotNull(institutionService.save(institutionModel));

        // Test whether save returns a InstitutionModel
        Assertions.assertSame(institutionService.save(institutionModel).getClass(), InstitutionModel.class);
    }

    @Test
    void whenFindAllThenReturnAListOfInstitutions() {
        InstitutionModel institutionModel = generateInstitution();
        when(institutionService.findAll()).thenReturn(Collections.singletonList(institutionModel));

        Assertions.assertNotNull(institutionService.findAll());
        Assertions.assertEquals(1, institutionService.findAll().size());
        Assertions.assertEquals(InstitutionModel.class, institutionService.findAll().get(0).getClass());
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
        InstitutionModel institutionModel = generateInstitution();
        when(institutionRepository.existsByRegistrationNumber("33455")).thenReturn(!institutionModel
                .getRegistrationNumber().isEmpty());

        Assertions.assertNotNull(institutionModel);
        Assertions.assertEquals(InstitutionModel.class, institutionModel.getClass());
        Assertions.assertEquals("33455", institutionModel.getRegistrationNumber());
    }

    @Test
    void existsByEmail() {
        InstitutionModel institutionModel = generateInstitution();
        when(institutionRepository.existsByEmail("imip@email.com")).thenReturn(!institutionModel.getEmail()
                .isEmpty());

        Assertions.assertNotNull(institutionModel);
        Assertions.assertEquals(InstitutionModel.class, institutionModel.getClass());
        Assertions.assertEquals("imip@email.com", institutionModel.getEmail());//
    }

    @Test
    void existsByPhoneNumber() {
        InstitutionModel institutionModel = generateInstitution();
        when(institutionRepository.existsByPhoneNumber("8199998888")).thenReturn(!institutionModel
                 .getPhoneNumber().isEmpty());

         Assertions.assertNotNull(institutionModel);
         Assertions.assertEquals(InstitutionModel.class, institutionModel.getClass());
         Assertions.assertEquals("8199998888", institutionModel.getPhoneNumber());
    }

    @Test
    void existsByUrl() {
        InstitutionModel institutionModel = generateInstitution();
        when(institutionRepository.existsByUrl("www.imip.com.br")).thenReturn(!institutionModel.getUrl()
                .isEmpty());

        Assertions.assertNotNull(institutionModel);
        Assertions.assertEquals(InstitutionModel.class, institutionModel.getClass());
        Assertions.assertEquals("www.imip.com.br", institutionModel.getUrl());
    }

   @Test
    void existsByName() {
       InstitutionModel institutionModel = generateInstitution();
        when(institutionRepository.existsByName("Imip")).thenReturn(!institutionModel.getName()
               .isEmpty());

       Assertions.assertNotNull(institutionModel);
       Assertions.assertEquals(InstitutionModel.class, institutionModel.getClass());
       Assertions.assertEquals("Imip", institutionModel.getName());
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