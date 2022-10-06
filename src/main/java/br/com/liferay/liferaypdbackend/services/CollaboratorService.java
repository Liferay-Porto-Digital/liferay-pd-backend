package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.repositories.ICollaboratorRepository;
import org.springframework.stereotype.Service;

@Service
public class CollaboratorService {

    //region INJECTIONS
    final ICollaboratorRepository collaboratorRepository;

    public CollaboratorService(ICollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }
    //endregion
}
