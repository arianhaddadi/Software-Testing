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
public class PetServiceTestOwnerMock { // Mockisty
	@Mock //Mock Object
	PetTimedCache petTimedCache;

	@Mock // Mock Object
	OwnerRepository ownerRepository;

	@Spy // Spy Object
	Logger logger;

	@InjectMocks
	private PetService petService;

	private Owner ownerMock;
	private int ownerMockId;

	@Before
	public void setUp() throws Exception {
		ownerMockId = 4;
		ownerMock = mock(Owner.class); // Mock Object
		doReturn(ownerMockId).when(ownerMock).getId();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void should_petserviceReturnProperOwner_findOwner() {//Classical Testing
		doReturn(ownerMock).when(ownerRepository).findById(ownerMockId);
		Owner owner = petService.findOwner(ownerMockId);
		assertEquals((int) owner.getId(), ownerMockId);
	}


	@Test
	public void should_loggerLogProperMessage_newPet() {
		petService.newPet(ownerMock);
		verify(logger).info("add pet for owner {}", ownerMockId); // Behavior Verification
	}

	@Test
	public void should_addPetMethodRunOnOwnerRepository_newPet() {
		petService.newPet(ownerMock);
		verify(ownerMock).addPet(any()); // Behavior Verification
	}

}
