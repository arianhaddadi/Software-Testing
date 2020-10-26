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
public class PetServiceTestPetAndOwnerMocks {
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
	private int ownerMockId;
	private int petMockId;

    @Before
    public void setUp() throws Exception {
    	ownerMockId = 4;
    	ownerMock = mock(Owner.class);

    	petMockId = 3;
    	petMock = mock(Pet.class);
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
