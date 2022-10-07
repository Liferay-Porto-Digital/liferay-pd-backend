package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.IFormRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormService {

    //region INJECTIONS
    final IFormRepository formRepository;

    public FormService(IFormRepository formRepository) {
        this.formRepository = formRepository;
    }
    //endregion

    //region METHODS

    /**
     * Save form in database
     * @param formModel
     */
    @Transactional
    @Modifying
    public void save(FormModel formModel) {
        formRepository.save(formModel);
    }

    /**
     * Delete/Drop form in the database
     * @param formModel
     */
    @Transactional
    @Modifying
    public void delete(FormModel formModel) {
        formRepository.delete(formModel);
    }

    /**
     * Get all forms from the database
     * @return List
     */
    public List<FormModel> getAllForms() {
        return formRepository.findAll();
    }
    //endregion
}
