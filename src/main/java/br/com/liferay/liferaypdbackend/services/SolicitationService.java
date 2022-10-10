package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.SolicitationModel;
import br.com.liferay.liferaypdbackend.repositories.ISolicitationRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SolicitationService {

    //region INJECTIONS
    final ISolicitationRepository solicitationRepository;

    public SolicitationService(ISolicitationRepository solicitationRepository) {
        this.solicitationRepository = solicitationRepository;
    }
    //endregion

    //region METHODS

    /**
     * Save solicitation in database
     * @param solicitationModel
     */
    @Transactional
    @Modifying
    public void saveSolicitation(SolicitationModel solicitationModel) {
        solicitationRepository.save(solicitationModel);
    }

    /**
     * Get all solicitations from the database
     * @return List
     */
    public List<SolicitationModel> getAllSolicitations() {
        return solicitationRepository.findAll();
    }
    //endregion
}
