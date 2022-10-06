package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.repositories.IFormRepository;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    //region INJECTIONS
    final IFormRepository formRepository;

    public FormService(IFormRepository formRepository) {
        this.formRepository = formRepository;
    }
    //endregion
}
