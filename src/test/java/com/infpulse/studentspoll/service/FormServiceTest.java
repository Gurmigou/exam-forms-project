package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.exceptions.NoPermissionException;
import com.infpulse.studentspoll.exceptions.NotFoundException;
import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.formDto.FormDto;
import com.infpulse.studentspoll.repository.FormsRepository;
import com.infpulse.studentspoll.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.internal.matchers.Equality.areEqual;

@SpringBootTest(classes = FormService.class)
public class FormServiceTest {

	@MockBean
	protected FormsRepository formsRepository;
	@MockBean
	protected UserRepository userRepository;
	@SpyBean
	protected ModelMapper modelMapper;

	@Autowired
	protected FormService formService;


	@Test
	public void shouldReturnSavedForm() {
		User user = new User();
		user.setLogin("username");
		Form form = new Form(1L, "form name", null, null,
				null, LocalDateTime.of(2022, 10, 23, 17, 0), null, null,
				null, null);
		FormDto formDto = modelMapper.map(form, FormDto.class);

		when(userRepository.findByLogin(any())).thenReturn(Optional.of(user));
		when(formsRepository.save(any())).thenReturn(form);

		areEqual(formDto,  formService.saveForm(formDto, "username"));
	}

	@Test
	public void shouldReturnOwnedForms() {
		Form form0 = new Form(0L, "form 0", null, null
				, null, null, null, null, null, null);
		Form form1 = new Form(1L, "form 1", null, null
				, null, null, null, null, null, null);
		Form form2 = new Form(2L, "form 2", null, null
				, null, null, null, null, null, null);
		List<Form> forms = List.of(form0, form1, form2);
		User user = new User();
		user.setLogin("username");
		List<FormDto> formDtos = modelMapper.map(forms, new TypeToken<List<FormDto>>() {}.getType());

		when(userRepository.findByLogin(any())).thenReturn(Optional.of(user));
		when(formsRepository.findAllByOwner(any())).thenReturn(forms);

		areEqual(formDtos, formService.getOwnedForms("username"));
	}

	@Test
	public void shouldThrowNotFoundWhenDeleteForm() {
		assertThrows(NotFoundException.class, () -> formService.deleteForm(1L, "username"));
	}

	@Test
	public void shouldThrowNoPermissionWhenDeleteForm() {
		User user = new User();
		user.setLogin("user2");
		Form form = new Form(1L, "form 0", user, null
				, null, null, null, null, null, null);

		when(userRepository.findByLogin(any())).thenReturn(Optional.of(user));
		when(formsRepository.findById(anyLong())).thenReturn(Optional.of(form));

		assertThrows(NoPermissionException.class, () -> formService.deleteForm(1L, "username"));
	}

	@Test
	public void shouldReturnRequestedForm() {
		Form form = new Form(1L, "form 0", null, null
				, null, LocalDateTime.of(2022, 10, 23, 17, 0),
				null, null, null, null);

		FormDto formDto = modelMapper.map(form, FormDto.class);

		when(formsRepository.findById(1L)).thenReturn(Optional.of(form));

		areEqual(formDto, formService.getForm(1L));
	}

}
