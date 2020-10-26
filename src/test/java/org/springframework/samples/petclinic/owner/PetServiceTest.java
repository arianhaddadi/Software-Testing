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

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findOwner() {
		int ownerId = 4;
		petService.findOwner(ownerId);
		verify(logger).info("find owner {}", ownerId); // Behavior Verification
		verify(ownerRepository).findById(ownerId); // Behavior Verification


//		int ownerId = 4;
//		Owner owner = mock(Owner.class);
//		doReturn(ownerId).when(owner).getId();
//		doReturn(owner).when(ownerRepository).findById(ownerId);
//		owner = petService.findOwner(ownerId);
//		verify(logger).info("find owner {}", ownerId);
//		verify(ownerRepository).findById(ownerId);
//		assertEquals((int) owner.getId(), ownerId);
    }

    @Test
    public void newPet() {
    	Owner owner = Mockito.mock(Owner.class); //Stub Object
    	int ownerId = 3;
    	doReturn(ownerId).when(owner).getId();

    	petService.newPet(owner);
    	verify(logger).info("add pet for owner {}", ownerId); // Behavior Verification
    	verify(owner).addPet(any()); // Behavior Verification
    }

    @Test
    public void findPet() {
    	int petId = 4;
    	Pet pet = petService.findPet(petId);
		assertNull(pet); // State Verification

    	pet = mock(Pet.class); // Mock Object
    	doReturn(petId).when(pet).getId();
		doReturn(pet).when(petTimedCache).get(petId);
    	pet = petService.findPet(petId);
    	assertNotNull(pet);// State Verification
		assertEquals((int) pet.getId(), petId);// State Verification
		verify(petTimedCache, times(2)).get(petId);// Behavior Verification
		verify(logger, times(2)).info("find pet by id {}", petId);// Behavior Verification

    }

    @Test
    public void savePet() {
		int petId = 32;
		Pet pet = mock(Pet.class); // Stub Object
    	doReturn(petId).when(pet).getId();

    	Owner owner = mock(Owner.class); // Mock Object

    	petService.savePet(pet, owner);

    	verify(owner).addPet(pet); // Behavior Verification
		verify(petTimedCache).save(pet);// Behavior Verification
		verify(logger).info("save pet {}", petId);// Behavior Verification
    }
}
