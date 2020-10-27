package org.springframework.samples.petclinic.utility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.samples.petclinic.owner.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


//As it is mentioned in fowler article in section Choosing Between the Differences
//for a cache it is wiser to choose behavior verification. It is not an awkward collaboration but
//it in this case it is really hard to use state verification because we cant tell from state
//of the cache whether it missed or hit. Because each item in cache gets cleared after some expire time,
//we cannot tell for a test whether an item is still in the cache or not so we cannot know the exact state.
//And for the repository, to not change the real repository it would be better
//to be mockist and use mock to not change the real repository. So the best practice would be to
//use behaviour verification and be mockist.
@RunWith(MockitoJUnitRunner.class)
public class PetTimedCacheTest {
	@Mock
	PetRepository petRepository; //Mock Object

	private PetTimedCache petTimedCache;

	private Pet testPet;

	private Integer testPetId;

	@Before
	public void setUp() throws Exception {
		petTimedCache = new PetTimedCache(petRepository);
		testPet = mock(Pet.class);
		testPetId = testPet.getId();
	}

	@After
	public void tearDown() throws Exception {
		petTimedCache = null;
	}

	@Test //Behavior verification, mockist
	public void should_getFromRepository_whenMiss() {
		when(petRepository.findById(testPetId)).thenReturn(testPet);
		petTimedCache.get(testPetId);
		assertEquals(petTimedCache.get(testPetId), testPet);
		verify(petRepository).findById(testPetId);
	}

	@Test //Behavior verification, mockist
	public void should_notCallFindByIdOnRepository_whenHit() {
		when(petRepository.findById(testPetId)).thenReturn(testPet);
		petTimedCache.get(testPetId);
		petTimedCache.get(testPetId);
		verify(petRepository, times(1)).findById(testPetId);
	}

	@Test //Behavior verification, mockist
	public void should_saveToRepository_whenSave() {
		petTimedCache.save(testPet);
		verify(petRepository).save(testPet);
	}

	@Test //Behavior verification, mockist
	public void should_miss_afterExpiryTime() {
		when(petRepository.findById(testPetId)).thenReturn(testPet);
		petTimedCache.get(testPetId);
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		petTimedCache.get(testPetId);
		verify(petRepository, times(2)).findById(testPetId);
	}

}

