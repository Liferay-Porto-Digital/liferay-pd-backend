package br.com.liferay.liferaypdbackend.controllers;

import br.com.liferay.liferaypdbackend.dtos.InstitutionDTO;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.services.InstitutionService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/institution")
public class InstitutionController {
    //region INJECTIONS
    final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }
    //endregion

    //region ENDPOINTS
    @GetMapping()
    public ResponseEntity<List<InstitutionModel>> getAllInstitutions() {
        return ResponseEntity.status(HttpStatus.OK).body(institutionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInstitutionById(@PathVariable(value = "id") UUID id) {
        Optional<InstitutionModel> institutionModelOptional = institutionService.findById(id);

        return institutionModelOptional.<ResponseEntity<Object>>map(institutionModel -> ResponseEntity.status(HttpStatus.OK).body(institutionModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND: Couldn't find institution"));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> saveInstitution(@RequestBody @Valid InstitutionDTO institutionDTO) {
        institutionDTO.setName(institutionDTO.getName().toLowerCase());

        // TODO: Make and call method of verification in order to implement less code on the controller
        if (institutionService.existsByRegistrationNumber(institutionDTO.getRegistrationNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: Registration Number already belongs to an existing institution");
        }
        if (institutionService.existsByEmail(institutionDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: Email already belongs to an existing institution");
        }
        if (institutionService.existsByPhoneNumber(institutionDTO.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: Phone Number already belongs to an existing institution");
        }
        if (institutionService.existsByUrl(institutionDTO.getUrl())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: URL already belongs to an existing institution");
        }
        if (institutionService.existsByName(institutionDTO.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: Name already belongs to an existing institution");
        }

        InstitutionModel institutionModel = new InstitutionModel();
        BeanUtils.copyProperties(institutionDTO, institutionModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(institutionService.save(institutionModel));
    }
    //endregion
}
