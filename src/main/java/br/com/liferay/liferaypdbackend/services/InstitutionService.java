package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.dtos.InstitutionDTO;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionService {

    //region INJECTIONS
    @Autowired
    InstitutionRepository institutionRepository;
    //endregion

    //region METHODS

    /**
     * Save institution in database
     * @param institutionModel
     */
    @Transactional
    @Modifying
    public InstitutionModel save(InstitutionModel institutionModel) {
        return institutionRepository.save(institutionModel);
    }

    /**
     * Get all institutions from the database
     * @return List
     */
    public List<InstitutionModel> findAll() {
        if (!institutionRepository.findAll().isEmpty()) {
            return institutionRepository.findAll();
        }
        return null;
    }

    public Optional<InstitutionModel> findByName(String name) {
        if (institutionRepository.findByName(name).isPresent()) {
            return institutionRepository.findByName(name);
        }
        return Optional.empty();
    }

    public boolean existsByRegistrationNumber(String registrationNumber) {
        return institutionRepository.existsByRegistrationNumber(registrationNumber);
    }

    public boolean existsByEmail(String email) {
        return institutionRepository.existsByEmail(email);
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return institutionRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean existsByUrl(String url) {
        return institutionRepository.existsByUrl(url);
    }

    public boolean existsByName(String name) {
        return institutionRepository.existsByName(name);
    }

    public List<InstitutionModel> getInstitutionWithMoreSolicitations() {
        return institutionRepository.getInstitutionListWithMoreSolicitations();
    }

    public List<InstitutionModel> getInstitutionWithLessSolicitations() {
        return institutionRepository.getInstitutionListWithLessSolicitations();
    }

    public void fixInstitutionDtoFormat(InstitutionDTO institutionDTO) {
        institutionDTO.setName(institutionDTO.getName().trim().toUpperCase());
        institutionDTO.setStreet(institutionDTO.getStreet().trim().toUpperCase());
        institutionDTO.setCity(institutionDTO.getCity().trim().toUpperCase());
        institutionDTO.setState(institutionDTO.getState().trim().toUpperCase());
        institutionDTO.setDescription(institutionDTO.getDescription().trim().toUpperCase());
        institutionDTO.setEmail(institutionDTO.getEmail().trim());
        institutionDTO.setUrl(institutionDTO.getUrl().trim());
    }

    public ResponseEntity<Object> verifyInstitutionFields(InstitutionDTO institutionDTO) {
        if (existsByRegistrationNumber(institutionDTO.getRegistrationNumber())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("CONFLICT: Registration Number already belongs to an existing institution");
        }
        if (existsByEmail(institutionDTO.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("CONFLICT: Email already belongs to an existing institution");
        }
        if (existsByPhoneNumber(institutionDTO.getPhoneNumber())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("CONFLICT: Phone Number already belongs to an existing institution");
        }
        if (existsByUrl(institutionDTO.getUrl())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("CONFLICT: URL already belongs to an existing institution");
        }
        if (existsByName(institutionDTO.getName())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("CONFLICT: Name already belongs to an existing institution");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK: All fields according to rules");
    }
    //endregion
}
