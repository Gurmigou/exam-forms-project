package com.infpulse.studentspoll.controllers;

import com.infpulse.studentspoll.model.formDto.formHeaders.OwnedFormHeader;
import com.infpulse.studentspoll.model.formDto.formHeaders.PassedFormHeader;
import com.infpulse.studentspoll.model.formDto.ownedFormDto.FormDto;
import com.infpulse.studentspoll.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FormController {

    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping("/forms/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void newForm(@RequestBody @Valid FormDto formDto, Principal principal) {
        formService.saveForm(formDto, principal.getName());
    }

	@GetMapping("/forms/owned")
	public List<OwnedFormHeader> ownedForms(Principal principal) {
		return formService.getOwnedForms(principal.getName());
	}

	@GetMapping("/forms/available")
	public List<PassedFormHeader> availableForms(Principal principal) {
		return formService.getAvailableForms(principal.getName());
	}

	@GetMapping("/forms/{formId}")
	public FormDto getForm(@PathVariable long formId) {
		return formService.getForm(formId);
	}

	@DeleteMapping("/forms/{formId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteForm(@PathVariable long formId, Principal principal) {
		formService.deleteForm(formId, principal.getName());
	}


}
