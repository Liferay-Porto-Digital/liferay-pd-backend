package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.repositories.ICollaboratorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaboratorService {

    //region INJECTIONS
    final ICollaboratorRepository collaboratorRepository;

    public CollaboratorService(ICollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }
    //endregion

    //region METHODS

    /**
     * Get all collaborators from the databased
     * @return List
     */
    public List<CollaboratorModel> getAllCollaborators() {
        return collaboratorRepository.findAll();
    }
    //endregion
}
