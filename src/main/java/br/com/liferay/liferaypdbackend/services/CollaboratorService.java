package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.repositories.ICollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollaboratorService {
    @Autowired
    ICollaboratorRepository collaboratorRepository;
}
