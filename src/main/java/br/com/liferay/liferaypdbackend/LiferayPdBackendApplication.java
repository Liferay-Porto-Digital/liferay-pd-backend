package br.com.liferay.liferaypdbackend;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.repositories.CollaboratorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiferayPdBackendApplication implements CommandLineRunner {

    //region INJECTIONS
    final CollaboratorRepository collaboratorRepository;

    public LiferayPdBackendApplication(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }
    //endregion

    public static void main(String[] args) {
        SpringApplication.run(LiferayPdBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        if (collaboratorRepository.findAll().isEmpty()) {
            CollaboratorModel collaboratorModel = new CollaboratorModel("Maria", "Auxiliar Administrativo", true);
            collaboratorRepository.save(collaboratorModel);
        }
    }
}
