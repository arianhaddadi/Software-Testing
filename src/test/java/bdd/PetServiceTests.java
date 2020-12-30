package bdd;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;
import org.springframework.samples.petclinic.utility.PetTimedCache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PetServiceTests {

	@Autowired
	PetService petService;

	@Autowired
	OwnerRepository owners;

	@Autowired
	PetTimedCache pets;

	@Autowired
	PetTypeRepository petTypeRepository;

	@Autowired
	Logger logger;

	private Owner owner;
	private Owner foundOwner;
	private Pet addedPet;
	private Pet pet;
	private Pet foundPet;

	@Before("@sample_annotation")
	public void setup() {
		// sample setup code
	}

	// Shared among all
	@Given("There is a pet owner called {string} {string}")
	public void thereIsAPetOwnerCalled(String firstName, String lastName) {
		owner = new Owner();
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setAddress("Central Park");
		owner.setCity("New York");
		owner.setTelephone("09120000000");
		owners.save(owner);
	}

	// Find Owner Scenario
	@When("We perform find Owner service to find him")
	public void wePerformFindOwnerService() {
		foundOwner = petService.findOwner(owner.getId());
	}

	@Then("He is found successfully")
	public void HeIsFound() {
		assertEquals(owner.getId(), foundOwner.getId());
	}


	// New Pet Scenario
	@When("We perform new Pet service add a pet for him")
	public void wePerformNewPetService() {
		addedPet = petService.newPet(owner);
	}

	@Then("A new pet is added successfully for him")
	public void PetIsAdded() {
		assertEquals(addedPet.getId(), owner.getPets().get(0).getId());
	}

	// Save Pet Scenario & Find Pet Scenario
	@Given("There is a pet called {string}")
	public void thereIsAPetCalled(String name) {
		PetType petType = new PetType();
		petType.setName("dog");
		petTypeRepository.save(petType);

		pet = new Pet();
		pet.setName(name);
		pet.setType(petType);
	}

	// Save Pet Scenario
	@When("We perform save Pet service on that pet and him")
	public void wePerformSavePetService() {
		petService.savePet(pet, owner);
	}

	@When("We save the pet in the pet repository of the pet service")
	public void weSaveThePetInRepository() {
		pets.save(pet);
	}

	// Find Pet Scenario
	@When("We Set the owner of the pet to be him")
	public void weSetTheOwnerOfPet() {
		owner.addPet(pet);
	}

	@When("We perform find pet service to find the added pet")
	public void wePerformFindPetService() {
		foundPet = petService.findPet(pet.getId());
	}

	@Then("The Pet is found successfully")
	public void PetIsFound() {
		assertEquals(foundPet.getId(), pet.getId());
	}

	@Then("The Pet is saved successfully")
	public void PetIsSaved() {
		assertEquals(this.pets.get(pet.getId()).getId(), pet.getId());
		assertEquals(this.owner.getPets().get(0).getId(), pet.getId());
	}

}
