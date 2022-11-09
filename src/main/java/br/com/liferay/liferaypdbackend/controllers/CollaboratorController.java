package br.com.liferay.liferaypdbackend.controllers;

import br.com.liferay.liferaypdbackend.services.CollaboratorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> getAllCollaborators() {
        if (!collaboratorService.findAll().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(collaboratorService.findAll());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("NOT FOUND: No collaborator registered on the database.");
    }

    @GetMapping("collaborator/{name}")
    @ApiOperation(value = "Get collaborator on the database based on name")
    public ResponseEntity<Object> findCollaboratorByName(@PathVariable String name) {
        if (collaboratorService.findByName(name) != null) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(collaboratorService.findByName(name));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("NOT FOUND: No collaborator with given name registered on the database.");
    }
    //endregion
}
