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
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VisitRestControllerTest {

	@Mock
	ClinicService clinicService;

	@InjectMocks
	VisitRestController visitRestController;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_returnResponseEntityWithBadRequestWhenBindingResultHasErrors_updateVisit() {
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		doReturn(true).when(bindingResult).hasErrors();
		ResponseEntity responseEntity = visitRestController.updateVisit(2, null, bindingResult);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

	@Test
	public void should_returnResponseEntityWithNotFoundWhenCurrentVisitIsNull_updateVisit() {
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Visit visit = Mockito.mock(Visit.class);

		doReturn(new Pet()).when(visit).getPet();
		doReturn(false).when(bindingResult).hasErrors();
		doReturn(null).when(clinicService).findVisitById(anyInt());

		ResponseEntity responseEntity = visitRestController.updateVisit(2, visit, bindingResult);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND));
	}

	@Test
	public void should_returnResponseEntityWithNoContentWhenCurrentVisitAndVisitIsNotNull_updateVisit() {
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Visit visit = Mockito.mock(Visit.class);

		doReturn(new Pet()).when(visit).getPet();
		doReturn(false).when(bindingResult).hasErrors();
		doReturn(new Visit()).when(clinicService).findVisitById(anyInt());

		ResponseEntity responseEntity = visitRestController.updateVisit(2, visit, bindingResult);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
}
