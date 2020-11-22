package org.springframework.samples.petclinic.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class OwnerRestControllerTest {
	@Mock
	ClinicService clinicService;
	@InjectMocks
	private OwnerRestController ownerRestController;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void should_get_bad_request_status_on_null_owner() {
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		UriComponentsBuilder uriComponentsBuilder = Mockito.mock(UriComponentsBuilder.class);
		ResponseEntity<Owner> result = ownerRestController.updateOwner(0, null, bindingResult, uriComponentsBuilder);
		HttpStatus status = result.getStatusCode();
		assertEquals(HttpStatus.BAD_REQUEST, status);
	}

	@Test
	public void should_get_not_found_status_on_not_found_owner_id() {
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		UriComponentsBuilder uriComponentsBuilder = Mockito.mock(UriComponentsBuilder.class);
		Owner owner = Mockito.mock(Owner.class);
		doReturn(null).when(clinicService).findOwnerById(0);
		ResponseEntity<Owner> result = ownerRestController.updateOwner(0, owner, bindingResult, uriComponentsBuilder);
		HttpStatus status = result.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, status);
	}

	@Test
	public void should_get_no_content_status_on_normal_request() {
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		UriComponentsBuilder uriComponentsBuilder = Mockito.mock(UriComponentsBuilder.class);
		Owner owner = Mockito.mock(Owner.class);
		doReturn(owner).when(clinicService).findOwnerById(0);
		ResponseEntity<Owner> result = ownerRestController.updateOwner(0, owner, bindingResult, uriComponentsBuilder);
		HttpStatus status = result.getStatusCode();
		assertEquals(HttpStatus.NO_CONTENT, status);
	}
}
