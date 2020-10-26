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
public class PetServiceTest {
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
    	doReturn(ownerMockId).when(ownerMock).getId();

    	petMockId = 3;
    	petMock = mock(Pet.class);
    	doReturn(petMockId).when(petMock).getId();
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
	public void should_petserviceReturnProperOwner_findOwner() {//Classical Testing
		doReturn(ownerMock).when(ownerRepository).findById(ownerMockId);
		Owner owner = petService.findOwner(ownerMockId);
		assertEquals((int) owner.getId(), ownerMockId);
	}

	@Test
	public void should_findByIdMethodRunOnOwnerRepository_findOwner() {// Mockisty Method
		petService.findOwner(ownerMockId);
		verify(ownerRepository).findById(ownerMockId); // Behavior Verification
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

    @Test
    public void should_findPetReturnNullIfPetNotExists_findPet() {
    	Pet pet = petService.findPet(petMockId);
		assertNull(pet); // State Verification
    }

	@Test
	public void should_petServiceReturnProperPet_findPet() {
		doReturn(petMock).when(petTimedCache).get(petMockId);
		Pet pet = petService.findPet(petMockId);
		assertNotNull(pet);// State Verification
		assertEquals((int) pet.getId(), petMockId);// State Verification
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
