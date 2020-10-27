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
public class PetServiceTestPetAndOwnerMocks { // Mockisty
	@Mock //Mock Object
	PetTimedCache petTimedCache;

	@Mock // Mock Object
	OwnerRepository ownerRepository;

	@Spy // Spy Object
	Logger logger;

	@InjectMocks
	private PetService petService;

	private Owner ownerMock;
	private Pet petMock;
	private int petMockId;

    @Before
    public void setUp() throws Exception {
    	ownerMock = mock(Owner.class); // Mock Object

    	petMockId = 3;
    	petMock = mock(Pet.class); // Stub Object
    	doReturn(petMockId).when(petMock).getId();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_addPetMethodRunOnOwnerMock_savePet() {
		petService.savePet(petMock, ownerMock);
    	verify(ownerMock).addPet(petMock); // Behavior Verification
    }

	@Test
	public void should_loggerLogProperMessage_savePet() {
		petService.savePet(petMock, ownerMock);
		verify(logger).info("save pet {}", petMockId);// Behavior Verification
	}

	@Test
	public void should_saveMethodRunOnPetTimedCache_savePet() {
		petService.savePet(petMock, ownerMock);
		verify(petTimedCache).save(petMock);// Behavior Verification
	}

}
