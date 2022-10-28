package br.com.liferay.liferaypdbackend.controllers;

import br.com.liferay.liferaypdbackend.models.SolicitationModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.services.SolicitationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        registerSolicitation("Get solicitation registrations");
        return ResponseEntity.status(HttpStatus.FOUND).body(solicitationService.findAll());
    }

    @GetMapping("solicitation/recent")
    @ApiOperation(value = "Get all solicitations ordered by the new ones")
    public ResponseEntity<List<FormModel>> getAllRecentSolicitation() {
        registerSolicitation("Get all solicitations ordered by newer");
        return  ResponseEntity.status(HttpStatus.FOUND).body(solicitationService.findAllFormsOrderByNewer());
    }

    @GetMapping("solicitation/older")
    @ApiOperation(value = "Get all solicitations ordered by the old ones")
    public ResponseEntity<List<FormModel>> getAllOlderSolicitation() {
        registerSolicitation("Get all solicitations ordered by older");
        return ResponseEntity.status(HttpStatus.FOUND).body(solicitationService.findAllFormsAndOrderByOlder());
    }

    @GetMapping("solicitation/collaborator/{name}")
    @ApiOperation(value = "Get all solicitations done by given collaborator")
    public ResponseEntity<List<FormModel>> getAllSolicitationByCollaborator(@PathVariable String name) {
        registerSolicitation("Get solicitations done by collaborator");
        return ResponseEntity.status(HttpStatus.FOUND).body(solicitationService.findAllFormsByCollaboratorName(name));
    }

    @GetMapping("solicitations/institution/{name}")
    @ApiOperation(value = "Get all solicitations done to given institution")
    public ResponseEntity<List<FormModel>> getAllSolicitationByInstitution(@PathVariable String name) {
        registerSolicitation("Get solicitations for institution");
        return ResponseEntity.status(HttpStatus.FOUND).body(solicitationService.findAllFormsByInstitutionName(name));
    }

    @GetMapping("solicitation/donation")
    @ApiOperation(value = "Get all donation type solicitation")
    public ResponseEntity<List<FormModel>> getAllSolicitationTypeDonation() {
        registerSolicitation("Get donation form");
        return ResponseEntity.status(HttpStatus.FOUND).body(solicitationService.findAllDonationForms());
    }

    @GetMapping("solicitation/activity")
    @ApiOperation(value = "Get all activity type solicitation")
    public ResponseEntity<List<FormModel>> getAllSolicitationTypeActivity() {
        registerSolicitation("Get activity form");
        return ResponseEntity.status(HttpStatus.FOUND).body(solicitationService.findAllActivityForms());
    }

    public void registerSolicitation(String typeOfSolicitation) {
        SolicitationModel solicitationModel = new SolicitationModel(solicitationService.findCollaboratorByName("Maria"), typeOfSolicitation);
        solicitationService.save(solicitationModel);
    }
    //endregion
}
