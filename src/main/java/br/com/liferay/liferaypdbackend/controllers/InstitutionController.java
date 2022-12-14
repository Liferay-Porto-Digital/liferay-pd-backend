package br.com.liferay.liferaypdbackend.controllers;

import br.com.liferay.liferaypdbackend.dtos.InstitutionDTO;
import br.com.liferay.liferaypdbackend.models.InstitutionModel;
import br.com.liferay.liferaypdbackend.services.InstitutionService;
import br.com.liferay.liferaypdbackend.services.utils.ConsoleLogUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/")
public class InstitutionController {
    //region INJECTIONS
    @Autowired
    InstitutionService institutionService;
    //endregion

    //region ENDPOINTS
    @GetMapping("institution")
    @ApiOperation(value = "Get a list with all institutions on the database")
    public ResponseEntity<Object> getAllInstitutions() {
        try {
            if (!institutionService.findAll().isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(institutionService.findAll());
            }
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(institutionService.findAll());
        } catch (Exception e) {
            ConsoleLogUtil.log.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("INSTITUTION LIST NOT FOUND: Please register a institution before calling this endpoint again.");
        }
    }

    @GetMapping("institution/{name}")
    @ApiOperation(value = "Returns the institution filtered by the given name")
    public ResponseEntity<Object> findInstitutionByName(@RequestBody @PathVariable(value = "name") String name) {
        if (institutionService.findByName(name.trim().toUpperCase()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(institutionService.findByName(name.trim().toUpperCase()).get());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("INSTITUTION NOT FOUND: No institution with provided name");
    }

    @GetMapping("institution/more-solicitation")
    @ApiOperation(value = "Get all institutions ordered by the ones with most solicitations")
    public ResponseEntity<Object> getMoreSolicitation() {
        if (!institutionService.getInstitutionWithMoreSolicitations().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(institutionService.getInstitutionWithMoreSolicitations());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("INSTITUTION LIST NOT FOUND: Please register a institution before calling this endpoint again.");
    }

    @GetMapping("institution/less-solicitation")
    @ApiOperation(value = "Get all institutions ordered by the ones with fewer solicitations")
    public ResponseEntity<Object> getLessSolicitation() {
        if (!institutionService.getInstitutionWithLessSolicitations().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(institutionService.getInstitutionWithLessSolicitations());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("INSTITUTION LIST NOT FOUND: Please register a institution before calling this endpoint again.");
    }

    @PostMapping("institution")
    @ApiOperation(value = "Add new institution on the database")
    public ResponseEntity<Object> addInstitution(@RequestBody @Valid InstitutionDTO institutionDTO) {
        try {
            institutionService.fixInstitutionDtoFormat(institutionDTO);

            ResponseEntity<Object> verification = institutionService.verifyInstitutionFields(institutionDTO);

            if(!verification.getStatusCode().equals(HttpStatus.OK)) {
                return verification;
            }

            InstitutionModel institutionModel = new InstitutionModel();
            BeanUtils.copyProperties(institutionDTO, institutionModel);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(institutionService.save(institutionModel));
        } catch (Exception e) {
            ConsoleLogUtil.log.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("CONFLICT: Error while trying to save institution. Verify fields before continuing.");
        }
    }
    //endregion
}
