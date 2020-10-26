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
public class PetServiceTestNoPetAndOwnerMocks {
	@Mock //Mock Object
	PetTimedCache petTimedCache;

	@Mock // Mock Object
	OwnerRepository ownerRepository;

	@Spy // Spy Object
	Logger logger;

	@InjectMocks
	private PetService petService;

	private int ownerMockId;
	private int petMockId;

	@Before
	public void setUp() throws Exception {
		ownerMockId = 4;
		petMockId = 3;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void should_loggerLogProperMessage_findOwner() {//Classical Testing
		petService.findOwner(ownerMockId);
		verify(logger).info("find owner {}", ownerMockId); // Behavior Verification
	}

	@Test
	public void should_findOwnerReturnNullIfOwnerNotExists_findOwner() {//Classical Testing
		Owner owner = petService.findOwner(ownerMockId);
		assertNull(owner);
	}

	@Test
	public void should_findByIdMethodRunOnOwnerRepository_findOwner() {// Mockisty Method
		petService.findOwner(ownerMockId);
		verify(ownerRepository).findById(ownerMockId); // Behavior Verification
	}


	@Test
	public void should_findPetReturnNullIfPetNotExists_findPet() {
		Pet pet = petService.findPet(petMockId);
		assertNull(pet); // State Verification
	}

	@Test
	public void should_loggerLogProperMessage_findPet() {
		petService.findPet(petMockId);
		verify(logger).info("find pet by id {}", petMockId);// Behavior Verification
	}

	@Test
	public void should_getMethodRunOnPetTimedCache_findPet() {
		petService.findPet(petMockId);
		verify(petTimedCache).get(petMockId);// Behavior Verification
	}

}
