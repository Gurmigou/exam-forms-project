package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.exceptions.NoPermissionException;
import com.infpulse.studentspoll.exceptions.NotFoundException;
import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.formDto.FormDto;
import com.infpulse.studentspoll.repository.FormsRepository;
import com.infpulse.studentspoll.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormService {

    private final FormsRepository formsRepository;
    private final UserRepository userRepository;
	private final ModelMapper mapper;

    @Autowired
    public FormService(FormsRepository formsRepository, UserRepository userRepository, ModelMapper mapper) {
        this.formsRepository = formsRepository;
        this.userRepository = userRepository;
	    this.mapper = mapper;
    }

    public FormDto saveForm(FormDto formDto, String userName) {
	    User user = findUserByUsername(userName);
	    Form form = mapper.map(formDto, Form.class);
		form.setOwner(user);
        return mapper.map(formsRepository.save(form), FormDto.class);
    }

	private FormDto saveForm(FormDto formDto, User user) {
		Form form = mapper.map(formDto, Form.class);
		form.setOwner(user);
		return mapper.map(formsRepository.save(form), FormDto.class);
	}

	public FormDto updateForm(long formId, FormDto formDto, String userName) {
		User user = findUserByUsername(userName);
		Optional<Form> oldForm = formsRepository.findById(formId);
		if (oldForm.isPresent()) {
			Form form = oldForm.get();
			if (!form.getOwner().equals(user) /*|| form.isDeleted*/) {
				throw new NoPermissionException("Form " + oldForm.get().getId());
			}
			mapper.map(formDto, form);
			return mapper.map(formsRepository.save(form), FormDto.class);
		} else {
			return saveForm(formDto, user);
		}
	}

	public FormDto getForm(long formId) {
		return mapper.map(getFormEntity(formId), FormDto.class);
	}

	public Form getFormEntity(long formId) {
		Form form = formsRepository.findById(formId).orElseThrow(() -> new NotFoundException("Form " + formId));
		/*if (form.isDeleted) {
			throw new NotFoundException("Form " + formId);
		}*/
		return form;
	}

	public void deleteForm(long formId, String userName) {
		Form form = formsRepository.findById(formId).orElseThrow(() -> new NotFoundException("Form " + formId));
		if (form.getOwner().getLogin().equals(userName)) {
//		    formsRepository.setDeleted(formId);
		} else {
			throw new NoPermissionException("Form " + formId);
		}
	}

	public List<FormDto> getOwnedForms(String userName) {
		User user = findUserByUsername(userName);
		List<Form> forms = formsRepository.findOwnedForms(user)/*.stream()
				.filter(form -> !form.isDeleted)
				.collect(Collectors.toList())*/;
		return mapper.map(forms, new TypeToken<List<FormDto>>() {}.getType());
	}

	private User findUserByUsername(String userName) {
		return userRepository.findByLogin(userName).orElseThrow(() -> new NotFoundException("User " + userName));
	}
}
