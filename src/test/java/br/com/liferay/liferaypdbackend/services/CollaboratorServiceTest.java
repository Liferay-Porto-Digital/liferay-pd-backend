package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.repositories.CollaboratorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CollaboratorServiceTest {

    @Mock
    private CollaboratorRepository collaboratorRepository;

    @InjectMocks
    private CollaboratorService collaboratorService;

    private ArrayList<CollaboratorModel> generateRepositoryCollaborator() {
        ArrayList<CollaboratorModel> list = new ArrayList<>();
        CollaboratorModel collaborator1 = new CollaboratorModel (
                "gabriel",
                "developer",
                false
        );
        CollaboratorModel collaborator2 = new CollaboratorModel (
                "Maria",
                "Auxiliar Administrativo",
                true
        );
        list.add(collaborator1);
        list.add(collaborator2);
        return list;
    }

    @Test
    void whenFindAllThenReturnListCollaborators() {
        when(collaboratorRepository.findAll()).thenReturn(generateRepositoryCollaborator());
        Assertions.assertNotNull(collaboratorService.findAll());
        Assertions.assertEquals(2, collaboratorService.findAll().size());

    }

    @Test
    void whenFindNameThenReturnCollaborator() {
        CollaboratorModel collaborator1 = new CollaboratorModel (
                "gabriel",
                "developer",
                false
        );
        Optional<CollaboratorModel> user = Optional.of(collaborator1);
        when(collaboratorRepository.findByName("gabriel")).thenReturn(user);
        Assertions.assertNotNull(collaboratorService.findByName("gabriel"));
        Assertions.assertEquals(collaborator1.getName(), collaboratorService.findByName("gabriel").getName());
    }
}

