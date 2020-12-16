package org.springframework.samples.petclinic.owner;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.validation.Errors;

import javax.validation.constraints.AssertFalse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class PetValidatorTest {

	private static final String REQUIRED = "required";

	private PetValidator petValidator;

	private Pet petMock;

	private Errors errorsMock;

	@BeforeEach
	public void setUp() throws Exception {
		petMock = mock(Pet.class);
		errorsMock = mock(Errors.class);
		petValidator = new PetValidator();
	}

	@Test
	public void should_errorsReqajectValueNameWhenNameIsEmpty_validate() {
		doReturn(null).when(petMock).getName();
		petValidator.validate(petMock, errorsMock);
		verify(errorsMock, times(1)).rejectValue("name", REQUIRED, REQUIRED);
	}


	@Test
	public void should_errorsDontRejectValueAnythingWhenNameIsSomething_validate() {
		doReturn("name").when(petMock).getName();
		petValidator.validate(petMock, errorsMock);
		verify(errorsMock, times(0)).rejectValue("name", REQUIRED, REQUIRED);
	}

	@Test
	public void should_errorsRejectValueTypeWhenTypeIsNull_validate() {
		doReturn(null).when(petMock).getType();
		doReturn(true).when(petMock).isNew();
		petValidator.validate(petMock, errorsMock);
		verify(errorsMock, times(1)).rejectValue("type", REQUIRED, REQUIRED);
	}

	@Test
	public void should_errorsDontRejectValueAnythingWhenTypeIsNotNull_validate() {
		doReturn(new PetType()).when(petMock).getType();
		doReturn(true).when(petMock).isNew();
		petValidator.validate(petMock, errorsMock);
		verify(errorsMock, times(0)).rejectValue("type", REQUIRED, REQUIRED);
	}

	@Test
	public void should_errorsRejectValueBirthDateWhenBirthIsNull_validate() {
		doReturn(null).when(petMock).getBirthDate();
		petValidator.validate(petMock, errorsMock);
		verify(errorsMock, times(1)).rejectValue("birthDate", REQUIRED, REQUIRED);
	}

	@Test
	public void should_returnFalseWhenClassIsNotAssignable_support() {
		assertFalse(petValidator.supports(Errors.class));
	}

	@Test
	public void should_returnTrueWhenClassIsAssignable_support() {
		assertTrue(petValidator.supports(Pet.class));
	}

}
