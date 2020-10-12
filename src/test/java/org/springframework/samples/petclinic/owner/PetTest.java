package org.springframework.samples.petclinic.owner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.samples.petclinic.visit.Visit;
import java.time.LocalDate;
import java.util.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PetTest {

	private Integer age;
	private List<Visit> expected;
	private Pet pet;
	public static ArrayList<Visit> visits;

	@Before
	public void setUp() {
		this.pet = new Pet();
		this.pet.setBirthDate(LocalDate.of(2000, 6, 24));
		for(Visit visit : visits)
			this.pet.addVisit(visit);
	}

	private static void generateVisits() {
		visits = new ArrayList<>();
		visits.add(new Visit().setDate(LocalDate.of(2003, 3,12)));
		visits.add(new Visit().setDate(LocalDate.of(2019, 7,5)));
		visits.add(new Visit().setDate(LocalDate.of(2008, 5,24)));
		visits.add(new Visit().setDate(LocalDate.of(2016, 12,20)));
		visits.add(new Visit().setDate(LocalDate.of(2010, 1,9)));
		visits.add(new Visit().setDate(LocalDate.of(2008, 8,16)));
		visits.add(new Visit().setDate(LocalDate.of(2017, 5,5)));
		visits.add(new Visit().setDate(LocalDate.of(2004, 4,28)));
		visits.add(new Visit().setDate(LocalDate.of(2013, 2,26)));
		visits.add(new Visit().setDate(LocalDate.of(2006, 11,3)));
	}

	public PetTest(Integer age, List<Visit> expected) {
		this.age = age;
		this.expected = expected;
	}

	@Parameterized.Parameters
	public static Collection parameters() {
		generateVisits();
		return Arrays.asList(new Object[][] {
			{1, Collections.emptyList()},
			{3, Collections.singletonList(visits.get(0))},
			{7, Arrays.asList(visits.get(0), visits.get(7), visits.get(9))},
			{8, Arrays.asList(visits.get(0), visits.get(2), visits.get(7), visits.get(9))},
			{9, Arrays.asList(visits.get(0), visits.get(2), visits.get(5), visits.get(7), visits.get(9))},
			{20, visits}
		});
	}

	@Test
	public void getVisitsUntilAgeTest () {
		List<Visit> actual = this.pet.getVisitsUntilAge(age);
		assertEquals(expected, actual);
	}

	@After
	public void tearDown() {
		this.pet = null;
	}
}
