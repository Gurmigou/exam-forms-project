package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.exceptions.NoPermissionException;
import com.infpulse.studentspoll.exceptions.NotFoundException;
import com.infpulse.studentspoll.model.entity.Block;
import com.infpulse.studentspoll.model.entity.Form;
import com.infpulse.studentspoll.model.entity.User;
import com.infpulse.studentspoll.model.formDto.FormDto;
import com.infpulse.studentspoll.repository.FormsRepository;
import com.infpulse.studentspoll.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FormService.class)
public class FormServiceTest {

	@MockBean
	protected FormsRepository formsRepository;
	@MockBean
	protected UserRepository userRepository;
	@MockBean
	protected ModelMapper modelMapper;

	@Autowired
	protected FormService formService;


	@Test
	public void shouldReturnSavedForm() {
		FormDto formDto = new FormDto();
		formDto.setTopicName("form name");
		LocalDateTime localDateTime = LocalDateTime.of(2022, 10, 23, 17, 0);
		formDto.setExpireDate(localDateTime);
		formDto.setListOfBlocks(List.of(new Block()));
		User user = new User();
		user.setLogin("username");
		Form form = new Form(1L, formDto.getTopicName(), null, null,
				null, formDto.getExpireDate(), null, null,
				formDto.getListOfBlocks(), null);

		when(modelMapper.map(any(FormDto.class), any())).thenReturn(form);
		when(modelMapper.map(any(Form.class), any())).thenReturn(formDto);
		when(userRepository.findByLogin(any())).thenReturn(Optional.of(user));
		when(formsRepository.save(any())).thenReturn(form);

		assertEquals(formDto, formService.saveForm(formDto, "username"));
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
		FormDto formDto0 = new FormDto();
		formDto0.setTopicName(form0.getTopicName());
		FormDto formDto1 = new FormDto();
		formDto0.setTopicName(form1.getTopicName());
		FormDto formDto2 = new FormDto();
		formDto0.setTopicName(form2.getTopicName());
		List<FormDto> formDtos = List.of(formDto0, formDto1, formDto2);

		when(modelMapper.map(any(), any())).thenReturn(formDtos);
		when(userRepository.findByLogin(any())).thenReturn(Optional.of(user));
		when(formsRepository.findAllByOwner(any())).thenReturn(forms);

		assertEquals(formDtos, formService.getOwnedForms("username"));
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
				, null, null, null, null, null, null);

		FormDto formDto = new FormDto();
		formDto.setTopicName(form.getTopicName());

		when(formsRepository.findById(1L)).thenReturn(Optional.of(form));
		when(modelMapper.map(any(Form.class), any())).thenReturn(formDto);

		assertEquals(formDto, formService.getForm(1L));
	}

}
