package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.formDto.ownedFormDto.FormDto;
import com.infpulse.studentspoll.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class FormController {

    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    /**
     * Creates a form
     */
    @PostMapping("/forms/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> newForm(@RequestBody @Valid FormDto formDto, Principal principal) {
        Optional<Form> form = formService.saveForm(formDto, principal.getName());
        return ResponseEntity.of(form);
    }

    /**
     * @return a list of forms that the user has created
     */
    @GetMapping("/forms/owned")
    public ResponseEntity<?> ownedForms(Principal principal) {
        return ResponseEntity.ok(formService.getOwnedForms(principal.getName()));
    }

    /**
     * @return a list of forms that the user has passed
     */
    @GetMapping("/forms/available")
    public ResponseEntity<?> availableForms(Principal principal) {
        return ResponseEntity.ok(formService.getAvailableForms(principal.getName()));
    }

    /**
     * @return a result list of students who passed the form with id {@code formId}
     */
    @GetMapping("/forms/valuation/{formId}")
    public ResponseEntity<?> userFormValuation(Principal principal, @PathVariable long formId) {
        return ResponseEntity.ok(formService.getFormValuation(principal.getName(), formId));
    }

    /**
     * @return a form as a test for the students
     */
    @GetMapping("/forms/{formId}")
    public ResponseEntity<?> getForm(@PathVariable long formId, Principal principal) {
        try {
            return ResponseEntity.of(formService.getForm(formId, principal.getName()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
    }

    /**
     * Deletes a form by id
     */
    @DeleteMapping("/forms/{formId}")
    public ResponseEntity<?> deleteForm(@PathVariable long formId, Principal principal) {
        formService.deleteForm(formId, principal.getName());
        return ResponseEntity.ok().build();
    }
}
