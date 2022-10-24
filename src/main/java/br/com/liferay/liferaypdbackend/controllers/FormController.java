package br.com.liferay.liferaypdbackend.controllers;

import br.com.liferay.liferaypdbackend.dtos.FormDTO;
import br.com.liferay.liferaypdbackend.models.creator.FormFactoryMethod;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.services.FormService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/")
public class FormController {
    //region INJECTIONS
    final FormService formService;
    final FormFactoryMethod formFactoryMethod;

    public FormController(FormService formService, FormFactoryMethod formFactoryMethod) {
        this.formService = formService;
        this.formFactoryMethod = formFactoryMethod;
    }
    //endregion

    //region DESCRIPTION
    @GetMapping("form")
    @ApiOperation(value = "Get a list with all forms on the database")
    public ResponseEntity<List<FormModel>> getAllForms() {
        if (!formService.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(formService.findAll());
        }
        return null;
    }

    @PostMapping("form/add/donation")
    @ApiOperation(value = "Add a donation form on the database")
    public ResponseEntity<FormModel> addDonationForm(FormDTO formDTO) {
        FormModel donationFormModel = formFactoryMethod.createForm(
                "donation",
                formService.getCollaborator(),
                formService.saveOrGetInstitution(formDTO),
                formDTO.getNameContact(),
                formDTO.getLastNameContact(),
                formDTO.getDateOfEvent(),
                formDTO.getValue()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(formService.save(donationFormModel));
    }

    @PostMapping("form/add/activity")
    @ApiOperation(value = "Add am activity form on the database")
    public ResponseEntity<FormModel> addActivityForm(FormDTO formDTO) {
        FormModel donationFormModel = formFactoryMethod.createForm (
                "activity",
                formService.getCollaborator(),
                formService.saveOrGetInstitution(formDTO),
                formDTO.getNameContact(),
                formDTO.getLastNameContact(),
                formDTO.getDateOfEvent(),
                formDTO.getValue()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(formService.save(donationFormModel));
    }
    //endregion
}
