package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.concreteProduct.ActivityFormModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.FormRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class FormServiceTest {
    @Mock
    FormRepository formRepository;

    @InjectMocks
    FormService formService;

    private FormModel generateActivityForm() {
        return new ActivityFormModel();
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
    void saveOrGetInstitution() {
    }

    @Test
    void getCollaborator() {
    }
}