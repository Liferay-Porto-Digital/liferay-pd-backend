package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.repositories.IAddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    //region INJECTIONS
    final IAddressRepository addressRepository;

    public AddressService(IAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    //endregion
}
