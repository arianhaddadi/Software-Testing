package org.springframework.samples.petclinic.service;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.*;
import org.springframework.validation.BindingResult;
import static org.mockito.Mockito.*;


import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ClinicServiceImplTest {
	@Mock
	PetRepository petRepository;
	@Mock
	VetRepository vetRepository;
	@Mock
	OwnerRepository ownerRepository;
	@Mock
	VisitRepository visitRepository;
	@Mock
	SpecialtyRepository specialtyRepository;
	@Mock
	PetTypeRepository petTypeRepository;
	@Mock
	Pet pet;
	@InjectMocks
	private ClinicServiceImpl clinicService;

	@Before
	public void setUp() throws Exception {
		Vet vet = Mockito.mock(Vet.class);
		ArrayList<Vet> vets = new ArrayList<>();
		vets.add(vet);
		doReturn(vets).when(vetRepository).findAll();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = ClinicServiceImpl.VisitException.class)
	public void should_throw_exception_VisitException_when_last_is_null() throws Exception {
		Owner owner = Mockito.mock(Owner.class);
		ArrayList<Pet> pets = new ArrayList<> ();
		pets.add(pet);
		doReturn(Optional.ofNullable(null)).when(pet).getLastVisit();
		doReturn(pets).when(petRepository).findByOwner(owner);
		clinicService.visitOwnerPets(owner);
		verify(pet, times(1)).getLastVisit();
		verify(petRepository, times(1)).findByOwner(owner);
		verify(vetRepository, times(1)).findAll();
	}

	@Test(expected = ClinicServiceImpl.VisitException.class)
	public void should_throw_exception_when_going_to_age_if() throws Exception {
		Owner owner = Mockito.mock(Owner.class);
		ArrayList<Pet> pets = new ArrayList<> ();
		Visit visit = Mockito.mock(Visit.class);
		Optional<Visit> optionalVisit = Optional.of(visit);
		doReturn(optionalVisit).when(pet).getLastVisit();
		pets.add(pet);
		doReturn(java.sql.Date.valueOf("2019-01-01")).when(pet).getBirthDate();
		doReturn(java.sql.Date.valueOf("2020-05-01")).when(visit).getDate();
		doReturn(pets).when(petRepository).findByOwner(owner);
		clinicService.visitOwnerPets(owner);
		verify(pet, times(1)).getLastVisit();
		verify(petRepository, times(1)).findByOwner(owner);
		verify(vetRepository, times(1)).findAll();
		verify(pet, times(1)).getBirthDate();
		verify(visit, times(1)).getDate();
		verify(vetRepository, times(0)).save(any());
	}

	@Test
	public void should_get_last_visit_with_big_age() throws Exception {
		Owner owner = Mockito.mock(Owner.class);
		ArrayList<Pet> pets = new ArrayList<> ();
		Visit visit = Mockito.mock(Visit.class);
		Optional<Visit> optionalVisit = Optional.of(visit);
		doReturn(optionalVisit).when(pet).getLastVisit();
		pets.add(pet);
		doReturn(java.sql.Date.valueOf("2019-01-01")).when(pet).getBirthDate();
		doReturn(java.sql.Date.valueOf("2020-09-01")).when(visit).getDate();
		doReturn(pets).when(petRepository).findByOwner(owner);
		clinicService.visitOwnerPets(owner);
		verify(pet, times(1)).getLastVisit();
		verify(petRepository, times(1)).findByOwner(owner);
		verify(vetRepository, times(1)).findAll();
		verify(vetRepository, times(0)).save(any());
	}
}
