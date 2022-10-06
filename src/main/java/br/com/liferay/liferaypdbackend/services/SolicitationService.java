package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.repositories.ISolicitationRepository;
import org.springframework.stereotype.Service;

@Service
public class SolicitationService {

    //region INJECTIONS
    final ISolicitationRepository solicitationRepository;

    public SolicitationService(ISolicitationRepository solicitationRepository) {
        this.solicitationRepository = solicitationRepository;
    }
    //endregion
}
