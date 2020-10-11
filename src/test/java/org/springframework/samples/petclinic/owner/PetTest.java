package org.springframework.samples.petclinic.owner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.samples.petclinic.visit.Visit;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.*;


@RunWith(Theories.class)
public class PetTest {
	private Pet pet;

	@Before
	public void setUp() throws Exception {
		this.pet = new Pet();
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
	}

	@After
	public void tearDown() throws Exception {
		this.pet = null;
	}

	@DataPoints
	public static LocalDate[] localDatesFrom = {LocalDate.of(2000, Month.JANUARY, 16),
		LocalDate.of(1999, Month.OCTOBER, 23),
		LocalDate.of(1997, Month.MARCH, 5)  };

	@DataPoints
	public static LocalDate[] localDatesTo = {LocalDate.of(2001, Month.SEPTEMBER, 11),
		LocalDate.of(2006, Month.NOVEMBER, 17),
		LocalDate.of(2012, Month.JULY, 26)  };

    @Theory
    public void getVisitsBetween(LocalDate start, LocalDate end) {
    	assumeTrue(!start.equals(end));
//    	assumeTrue(Arrays.asList(localDatesFrom).contains(start));
//		assumeTrue(Arrays.asList(localDatesTo).contains(end));
		List<Visit> betweenVisits = this.pet.getVisitsBetween(start, end);
		for (Visit visit : betweenVisits) {
			assertTrue(visit.getDate().isAfter(start) && visit.getDate().isBefore(end));
		}
		for (Visit visit : this.pet.getVisits()) {
			if (!betweenVisits.contains(visit)) {
				assertFalse(visit.getDate().isAfter(start) && visit.getDate().isBefore(end));
			}
		}
	}
}
