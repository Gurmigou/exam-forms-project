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
import java.util.Objects;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class FormController {

    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping("/forms/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> newForm(@RequestBody @Valid FormDto formDto, Principal principal) {
        Form form = formService.saveForm(formDto, principal.getName());
        if (Objects.nonNull(form)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
    }

    @GetMapping("/forms/owned")
    public ResponseEntity<?> ownedForms(Principal principal) {
        return ResponseEntity.ok(formService.getOwnedForms(principal.getName()));
    }

    @GetMapping("/forms/available")
    public ResponseEntity<?> availableForms(Principal principal) {
        return ResponseEntity.ok(formService.getAvailableForms(principal.getName()));
    }

    @GetMapping("/forms/valuation/{formId}")
    public ResponseEntity<?> userFormValuation(Principal principal, @PathVariable long formId) {
        return ResponseEntity.ok(formService.getFormValuation(principal.getName(), formId));
    }

    @GetMapping("/forms/{formId}")
    public ResponseEntity<?> getForm(@PathVariable long formId) {
        FormDto formDto = formService.getForm(formId);
        if (Objects.nonNull(formDto)) {
            return ResponseEntity.ok(formDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/forms/{formId}")
    public ResponseEntity<?> deleteForm(@PathVariable long formId, Principal principal) {
        formService.deleteForm(formId, principal.getName());
        return ResponseEntity.ok().build();
    }
}
