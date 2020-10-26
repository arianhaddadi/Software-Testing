package org.springframework.samples.petclinic.owner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.samples.petclinic.utility.PetTimedCache;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PetServiceTestPetMock {
	@Mock //Mock Object
	PetTimedCache petTimedCache;

	@Mock // Mock Object
	OwnerRepository ownerRepository;

	@Spy // Spy Object
	Logger logger;

	@InjectMocks
	private PetService petService;

	private Pet petMock;
	private int petMockId;

	@Before
	public void setUp() throws Exception {
		petMockId = 3;
		petMock = mock(Pet.class);
		doReturn(petMockId).when(petMock).getId();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void should_petServiceReturnProperPet_findPet() {
		doReturn(petMock).when(petTimedCache).get(petMockId);
		Pet pet = petService.findPet(petMockId);
		assertNotNull(pet);// State Verification
		assertEquals((int) pet.getId(), petMockId);// State Verification
	}

}
