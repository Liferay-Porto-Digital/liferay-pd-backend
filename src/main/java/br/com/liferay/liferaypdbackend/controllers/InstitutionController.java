package br.com.liferay.liferaypdbackend.controllers;

import br.com.liferay.liferaypdbackend.dtos.InstitutionDTO;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.services.InstitutionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/")
public class InstitutionController {
    //region INJECTIONS
    final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }
    //endregion

    //region ENDPOINTS
    @GetMapping("institution")
    @ApiOperation(value = "Get a list with all institutions on the database")
    public ResponseEntity<List<InstitutionModel>> getAllInstitutions() {
        return ResponseEntity.status(HttpStatus.FOUND).body(institutionService.findAll());
    }

    @GetMapping("institution/{name}")
    @ApiOperation(value = "Returns the institution filtered by the given name")
    public ResponseEntity<InstitutionModel> findInstitutionByName(@PathVariable String name) {
        if (institutionService.findByName(name.trim().toLowerCase()).isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(institutionService.findByName(name.trim().toLowerCase()).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("institution/more-solicitation")
    @ApiOperation(value = "Get all institutions ordered by the ones with most solicitations")
    public List<InstitutionModel> getMoreSolicitation() {
        return institutionService.getInstitutionWithMoreSolicitations();
    }

    @GetMapping("institution/less-solicitation")
    @ApiOperation(value = "Get all institutions ordered by the ones with fewer solicitations")
    public List<InstitutionModel> getLessSolicitation() {
        return institutionService.getInstitutionWithLessSolicitations();
    }

    @PostMapping("institution")
    @ApiOperation(value = "Add new institution on the database")
    public ResponseEntity<Object> addInstitution(@RequestBody @Valid InstitutionDTO institutionDTO) {
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
        institutionModel.setName(institutionDTO.getName().trim().toLowerCase());

        return ResponseEntity.status(HttpStatus.CREATED).body(institutionService.save(institutionModel));
    }
    //endregion
}
