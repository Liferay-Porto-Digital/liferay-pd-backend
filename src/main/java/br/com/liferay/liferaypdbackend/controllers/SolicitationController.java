package br.com.liferay.liferaypdbackend.controllers;

import br.com.liferay.liferaypdbackend.models.SolicitationModel;
import br.com.liferay.liferaypdbackend.services.SolicitationService;
import br.com.liferay.liferaypdbackend.services.utils.ConsoleLogUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/")
public class SolicitationController {
    //region INJECTIONS
    final SolicitationService solicitationService;

    public SolicitationController(SolicitationService solicitationService) {
        this.solicitationService = solicitationService;
    }
    //endregion

    //region ENDPOINTS
    @GetMapping("solicitation")
    @ApiOperation(value = "Get all solicitations")
    public ResponseEntity<Object> getAllSolicitations() {
        if (!solicitationService.findAll().isEmpty()) {
            registerSolicitation("Get solicitation registrations");
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(solicitationService.findAll());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("NOT FOUND: No solicitations registered on the database");
    }

    @GetMapping("solicitation/recent")
    @ApiOperation(value = "Get all solicitations ordered by the new ones")
    public ResponseEntity<Object> getAllRecentSolicitation() {
        if (!solicitationService.findAllFormsOrderByNewer().isEmpty()) {
            registerSolicitation("Get all solicitations ordered by newer");
            return  ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(solicitationService.findAllFormsOrderByNewer());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("NOT FOUND: No solicitations registered on the database.");
    }

    @GetMapping("solicitation/older")
    @ApiOperation(value = "Get all solicitations ordered by the old ones")
    public ResponseEntity<Object> getAllOlderSolicitation() {
        if (!solicitationService.findAllFormsAndOrderByOlder().isEmpty()) {
            registerSolicitation("Get all solicitations ordered by older");
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(solicitationService.findAllFormsAndOrderByOlder());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("NOT FOUND: No solicitations registered on the database.");
    }

    @GetMapping("solicitation/collaborator/{name}")
    @ApiOperation(value = "Get all solicitations done by given collaborator")
    public ResponseEntity<Object> getAllSolicitationByCollaborator(@PathVariable String name) {
        if (!solicitationService.findAllFormsByCollaboratorName(name).isEmpty()) {
            registerSolicitation("Get solicitations done by collaborator");
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(solicitationService.findAllFormsByCollaboratorName(name));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("NOT FOUND: No solicitations done by collaborator or collaborator does not exists.");
    }

    @GetMapping("solicitations/institution/{name}")
    @ApiOperation(value = "Get all solicitations done to given institution")
    public ResponseEntity<Object> getAllSolicitationByInstitution(@PathVariable String name) {
        if (!solicitationService.findAllFormsByInstitutionName(name).isEmpty()) {
            registerSolicitation("Get solicitations for institution");
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(solicitationService.findAllFormsByInstitutionName(name));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("NOT FOUND: No solicitation registered for given institution or institution does not exists.");
    }

    @GetMapping("solicitation/donation")
    @ApiOperation(value = "Get all donation type solicitation")
    public ResponseEntity<Object> getAllSolicitationTypeDonation() {
        if (!solicitationService.findAllDonationForms().isEmpty()) {
            registerSolicitation("Get donation form");
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(solicitationService.findAllDonationForms());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("NOT FOUND: No solicitation of type donation registered on the database.");
    }

    @GetMapping("solicitation/activity")
    @ApiOperation(value = "Get all activity type solicitation")
    public ResponseEntity<Object> getAllSolicitationTypeActivity() {
        if (!solicitationService.findAllActivityForms().isEmpty()) {
            registerSolicitation("Get activity form");
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(solicitationService.findAllActivityForms());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("NOT FOUND: No solicitation of type activity registered on the database.");
    }

    public void registerSolicitation(String typeOfSolicitation) {
        try {
            SolicitationModel solicitationModel = new SolicitationModel(solicitationService.findCollaboratorByName("Maria"), typeOfSolicitation);
            solicitationService.save(solicitationModel);
        } catch (Exception e) {
            ConsoleLogUtil.log.info(Arrays.toString(e.getStackTrace()));
        }
    }
    //endregion
}
