package br.com.liferay.liferaypdbackend.controllers;

import br.com.liferay.liferaypdbackend.dtos.FormDTO;
import br.com.liferay.liferaypdbackend.models.creator.FormFactoryMethod;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.services.FormService;
import br.com.liferay.liferaypdbackend.services.utils.ConsoleLogUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/")
public class FormController {
    //region INJECTIONS
    @Autowired
    FormService formService;
    //endregion

    //region ENDPOINTS
    @GetMapping("form")
    @ApiOperation(value = "Get a list with all forms on the database")
    public ResponseEntity<Object> getAllForms() {
        try {
            if (!formService.findAll().isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(formService.findAll());
            }
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("NOT FOUND: No forms registered. Please register a form before continuing.");
        } catch (Exception e) {
            ConsoleLogUtil.log.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("NOT FOUND: No forms registered. Please register a form before continuing.");
        }
    }

    @PostMapping("form/add/donation")
    @ApiOperation(value = "Add a donation form on the database")
    public ResponseEntity<Object> addDonationForm(@RequestBody @Valid FormDTO formDTO) {
        try {
            FormModel donationFormModel = formService.createFormModel("donation", formDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(formService.updateInstitutionNumberOfActionsReceived(formService.save(donationFormModel)));
        } catch (Exception e) {
            ConsoleLogUtil.log.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("CONFLICT: Error while trying to save donation form. Verify fields before continuing.");
        }
    }

    @PostMapping("form/add/activity")
    @ApiOperation(value = "Add am activity form on the database")
    public ResponseEntity<Object> addActivityForm(@RequestBody @Valid FormDTO formDTO) {
        try {
            FormModel activityFormModel = formService.createFormModel("activity", formDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(formService.updateInstitutionNumberOfActionsReceived(formService.save(activityFormModel)));
        } catch (Exception e) {
            ConsoleLogUtil.log.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("CONFLICT: Error while trying to save activity form. Verify fields before continuing.");
        }
    }
    //endregion
}
