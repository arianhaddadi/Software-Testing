package org.springframework.samples.petclinic.utility;

import com.github.mryf323.tractatus.CACC;
import com.github.mryf323.tractatus.ClauseDefinition;
import com.github.mryf323.tractatus.UniqueTruePoint;
import com.github.mryf323.tractatus.Valuation;
import com.github.mryf323.tractatus.experimental.extensions.ReportingExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(ReportingExtension.class)
@ClauseDefinition(clause = 'a', def = "Side1 <= 0")
@ClauseDefinition(clause = 'b', def = "Side2 <= 0")
@ClauseDefinition(clause = 'c', def = "Side3 <= 0")
@ClauseDefinition(clause = 'd', def = "Side1+Side2 <= Side3")
@ClauseDefinition(clause = 'e', def = "Side2+Side3 <= Side1")
@ClauseDefinition(clause = 'f', def = "Side1+Side3 <= Side2")
class TriTypeTest {

	private static final Logger log = LoggerFactory.getLogger(TriTypeTest.class);

	@Test
	public void sampleTest() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(1,1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.EQUILATERAL, triClass);
	}

	@CACC(
		predicate = "a + b + c",
		majorClause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false),
		},
		predicateValue = true
	)
	@Test
	public void CACC_FirstPredicate_Test1() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;

		triClass = tryType.classifyTriangle(-1,1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
	}

	@CACC(
		predicate = "a + b + c",
		majorClause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false),
		},
		predicateValue = true
	)
	@Test
	public void CACC_FirstPredicate_Test2() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;

		triClass = tryType.classifyTriangle(1,-1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);

	}

	@CACC(
		predicate = "a + b + c",
		majorClause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true),
		},
		predicateValue = true
	)
	@Test
	public void CACC_FirstPredicate_Test3() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;

		triClass = tryType.classifyTriangle(1,1,-1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);

	}

	@CACC(
		predicate = "a + b + c",
		majorClause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false),
		},
		predicateValue = false
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false),
		},
		predicateValue = false
	)
	@CACC(
		predicate = "a + b + c",
		majorClause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false),
		},
		predicateValue = false
	)
	@Test
	public void CACC_FirstPredicate_Test4() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;

		triClass = tryType.classifyTriangle(1,1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.EQUILATERAL, triClass);
	}



	@CACC(
		predicate = "d + e + f",
		majorClause = 'd',
		valuations = {
			@Valuation(clause = 'd', valuation = true),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false),
		},
		predicateValue = true
	)
	@Test
	public void CACC_SecondPredicate_Test1() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;

		triClass = tryType.classifyTriangle(1,1,8);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
	}

	@CACC(
		predicate = "d + e + f",
		majorClause = 'e',
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = true),
			@Valuation(clause = 'f', valuation = false),
		},
		predicateValue = true
	)
	@Test
	public void CACC_SecondPredicate_Test2() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;

		triClass = tryType.classifyTriangle(8,1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
	}

	@CACC(
		predicate = "d + e + f",
		majorClause = 'f',
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = true),
		},
		predicateValue = true
	)
	@Test
	public void CACC_SecondPredicate_Test3() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;

		triClass = tryType.classifyTriangle(1,8,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);

	}

	@CACC(
		predicate = "d + e + f",
		majorClause = 'd',
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false),
		},
		predicateValue = false
	)
	@CACC(
		predicate = "d + e + f",
		majorClause = 'e',
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false),
		},
		predicateValue = false
	)
	@CACC(
		predicate = "d + e + f",
		majorClause = 'f',
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false),
		},
		predicateValue = false
	)
	@Test
	public void CACC_SecondPredicate_Test4() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;

		triClass = tryType.classifyTriangle(1,1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.EQUILATERAL, triClass);
	}


	/**
	 * TODO
	 * explain your answer here
	 * @param  a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @return
	 */
	private static boolean questionTwo(boolean a, boolean b, boolean c, boolean d, boolean e) {
		boolean predicate = false;
//		predicate = a predicate with any number of clauses
		return predicate;
	}
}
