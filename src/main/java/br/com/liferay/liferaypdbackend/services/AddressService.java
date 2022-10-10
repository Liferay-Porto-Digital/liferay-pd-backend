package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.AddressModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.IAddressRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    //region INJECTIONS
    final IAddressRepository addressRepository;

    public AddressService(IAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    //endregion

    //region METHODS

    /**
     * Save address in database
     * @param addressModel
     */
    @Transactional
    @Modifying
    public void saveForm(AddressModel addressModel) {
        addressRepository.save(addressModel);
    }

    /**
     * Get all address from the database
     * @return List
     */
    public List<AddressModel> getAllAddress() {
        return addressRepository.findAll();
    }
    //endregion
}
