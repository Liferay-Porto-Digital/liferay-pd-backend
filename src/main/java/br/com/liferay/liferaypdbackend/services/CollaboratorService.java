package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.repositories.CollaboratorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollaboratorService {
    //region INJECTIONS
    final CollaboratorRepository collaboratorRepository;

    public CollaboratorService(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }
    //endregion

    //region METHODS
    public List<CollaboratorModel> findAll() {
        if (!collaboratorRepository.findAll().isEmpty()) {
            return collaboratorRepository.findAll();
        }
        return new ArrayList<>();
    }

    public CollaboratorModel findByName(String name) {
        if (collaboratorRepository.findByName(name).isPresent()) {
            return collaboratorRepository.findByName(name).get();
        }
        return null;
    }
    //endregion
}
