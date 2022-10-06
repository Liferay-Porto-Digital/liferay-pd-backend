package br.com.liferay.liferaypdbackend;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.repositories.ICollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiferayPdBackendApplication implements CommandLineRunner {

    //region INJECTIONS
    final ICollaboratorRepository collaboratorRepository;

    public LiferayPdBackendApplication(ICollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }
    //endregion

    public static void main(String[] args) {
        SpringApplication.run(LiferayPdBackendApplication.class, args);
    }

    // TODO: See if the following logic should be implemented in somewhere else
    @Override
    public void run(String... args) throws Exception
    {
        if (collaboratorRepository.findAll().isEmpty()) {
            CollaboratorModel first = new CollaboratorModel("Maria", "Auxiliar Administrativo", true);
            collaboratorRepository.save(first);
        }
    }
}
