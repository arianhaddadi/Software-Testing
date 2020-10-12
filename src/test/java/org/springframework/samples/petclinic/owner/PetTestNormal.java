package org.springframework.samples.petclinic.owner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.samples.petclinic.visit.Visit;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PetTestNormal {
	private Pet pet;
    @Before
    public void setUp() throws Exception {
		this.pet = new Pet();
    }

    @After
    public void tearDown() throws Exception {
    	this.pet = null;
    }

	@Test
	public void getVisitsBetween() {
		Visit v1 = new Visit().setDate(LocalDate.of(2003, 3,12));
		this.pet.addVisit(v1);
		Visit v2 = new Visit().setDate(LocalDate.of(2019, 7,5));
		this.pet.addVisit(v2);
		Visit v3 = new Visit().setDate(LocalDate.of(2008, 5,24));
		this.pet.addVisit(v3);
		Visit v4 = new Visit().setDate(LocalDate.of(2016, 12,20));
		this.pet.addVisit(v4);
		Visit v5 = new Visit().setDate(LocalDate.of(2010, 1,9));
		this.pet.addVisit(v5);
		List<Visit> res1 = this.pet.getVisitsBetween(LocalDate.of(2001,2,3), LocalDate.of(2003,1,4));
		assertEquals(Collections.emptyList(), res1);
		List<Visit> res2 = this.pet.getVisitsBetween(LocalDate.of(2002,2,3), LocalDate.of(2007,1,4));
		assertEquals(Collections.singletonList(v1), res2);
		List<Visit> res3 = this.pet.getVisitsBetween(LocalDate.of(2002,2,3), LocalDate.of(2011,1,4));
		assertEquals(Arrays.asList(v1, v3, v5), res3);
		List<Visit> res4 = this.pet.getVisitsBetween(LocalDate.of(2009,2,3), LocalDate.of(2017,1,4));
		assertEquals(Arrays.asList(v4, v5), res4);
		List<Visit> res5 = this.pet.getVisitsBetween(LocalDate.of(2001,2,3), LocalDate.of(2010,1,4));
		assertNotEquals(Arrays.asList(v1, v2, v3, v4, v5), res5);
	}

	@Test
	public void getVisitsUntilAge() {
		this.pet.setBirthDate(LocalDate.of(2000, 10, 9));
		Visit v1 = new Visit().setDate(LocalDate.of(2003, 3,12));
		this.pet.addVisit(v1);
		Visit v2 = new Visit().setDate(LocalDate.of(2019, 7,5));
		this.pet.addVisit(v2);
		Visit v3 = new Visit().setDate(LocalDate.of(2008, 5,24));
		this.pet.addVisit(v3);
		Visit v4 = new Visit().setDate(LocalDate.of(2016, 12,20));
		this.pet.addVisit(v4);
		Visit v5 = new Visit().setDate(LocalDate.of(2010, 1,9));
		this.pet.addVisit(v5);
		List<Visit> res1 = this.pet.getVisitsUntilAge(1);
		assertEquals(Collections.emptyList(), res1);
		List<Visit> res2 = this.pet.getVisitsUntilAge(4);
		assertEquals(Collections.singletonList(v1), res2);
		List<Visit> res3 = this.pet.getVisitsUntilAge(11);
		assertEquals(Arrays.asList(v1, v3, v5), res3);
		List<Visit> res4 = this.pet.getVisitsUntilAge(20);
		assertEquals(Arrays.asList(v1, v2, v3, v4, v5), res4);
		List<Visit> res5 = this.pet.getVisitsUntilAge(2);
		assertNotEquals(Arrays.asList(v1, v2, v3, v4, v5), res5);
	}

    @Test
    public void getVisits() {
    	assertEquals(this.pet.getVisits().size(), 0);

		this.pet.addVisit(new Visit().setDate(LocalDate.of(2003, 3,12)));
		this.pet.addVisit(new Visit().setDate(LocalDate.of(2019, 7,5)));
		this.pet.addVisit(new Visit().setDate(LocalDate.of(2008, 5,24)));
		this.pet.addVisit(new Visit().setDate(LocalDate.of(2016, 12,20)));
		this.pet.addVisit(new Visit().setDate(LocalDate.of(2010, 1,9)));
		this.pet.addVisit(new Visit().setDate(LocalDate.of(2008, 8,16)));
		this.pet.addVisit(new Visit().setDate(LocalDate.of(2017, 5,5)));
		this.pet.addVisit(new Visit().setDate(LocalDate.of(2004, 4,28)));
		this.pet.addVisit(new Visit().setDate(LocalDate.of(2013, 2,26)));
		this.pet.addVisit(new Visit().setDate(LocalDate.of(2006, 11,3)));

		List<Visit> visits = this.pet.getVisits();
		assertEquals(visits.size(), 10);
		for (int i = 0; i < visits.size() - 1; i++) {
			assertTrue(visits.get(i).getDate().compareTo(visits.get(i + 1).getDate()) >= 0);
		}
    }

    @Test
    public void removeVisit() {
		assertEquals(this.pet.getVisits().size(), 0);

		Visit visit = new Visit().setDate(LocalDate.of(2019, 7,5));
		this.pet.addVisit(visit);

		this.pet.removeVisit(visit);
		assertEquals(this.pet.getVisits().size(), 0);

		visit = new Visit().setDate(LocalDate.of(2019, 4,16));
		this.pet.addVisit(visit);

		Visit visit2 = new Visit().setDate(LocalDate.of(2019, 7,5));
		this.pet.addVisit(visit2);

		this.pet.removeVisit(visit);
		assertEquals(this.pet.getVisits().size(), 1);

		this.pet.removeVisit(visit2);
		assertEquals(this.pet.getVisits().size(), 0);
    }

    @Test
    public void addVisit() {
    	assertEquals(this.pet.getVisits().size(), 0);

    	Visit visit = new Visit().setDate(LocalDate.of(2019, 7,5));
    	this.pet.addVisit(visit);

    	assertEquals(this.pet.getVisits().size(), 1);
    	assertEquals(this.pet.getVisits().get(0), visit);

    	visit = new Visit().setDate(LocalDate.of(2019, 3,23));
    	this.pet.addVisit(visit);

		assertEquals(this.pet.getVisits().size(), 2);
		assertEquals(this.pet.getVisits().get(1), visit);
	}
}
