package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.repositories.IInstitutionRepository;
import org.springframework.stereotype.Service;

@Service
public class InstitutionService {

    //region INJECTIONS
    final IInstitutionRepository iInstitutionRepository;

    public InstitutionService(IInstitutionRepository iInstitutionRepository) {
        this.iInstitutionRepository = iInstitutionRepository;
    }
    //endregion
}
