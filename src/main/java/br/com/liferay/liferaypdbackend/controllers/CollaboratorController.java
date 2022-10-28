package br.com.liferay.liferaypdbackend.controllers;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import br.com.liferay.liferaypdbackend.services.CollaboratorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/")
public class CollaboratorController {
    //region INJECTIONS
    final CollaboratorService collaboratorService;

    public CollaboratorController(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }
    //endregion

    //region ENDPOINTS
    @GetMapping("collaborator")
    @ApiOperation(value = "Get a list with all collaborators on the database")
    public ResponseEntity<List<CollaboratorModel>> getAllCollaborators() {
        return ResponseEntity.status(HttpStatus.FOUND).body(collaboratorService.findAll());
    }

    @GetMapping("collaborator/{name}")
    @ApiOperation(value = "Get collaborator on the database based on name")
    public ResponseEntity<CollaboratorModel> findCollaboratorByName(@PathVariable String name) {
        if (collaboratorService.findByName(name) != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(collaboratorService.findByName(name));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    //endregion
}
