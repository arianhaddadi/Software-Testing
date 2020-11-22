package org.springframework.samples.petclinic.utility;

import com.github.mryf323.tractatus.*;
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

	@ClauseCoverage(
		predicate = "a + b + c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'b', valuation = false)
		}
	)
	@Test
	public void CC_FirstPredicate_Test1() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(1,1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.EQUILATERAL, triClass);
	}

	//Here is the second test for the first predicate witch true value for each clause is covered
	//To have that, all the sides should be negative as we can see in the test. Now for each clause
	//in the first predicate, true and false values are covered. (CC)
	@ClauseCoverage(
		predicate = "a + b + c",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'b', valuation = true)
		}
	)
	@Test
	public void CC_FirstPredicate_Test2() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(-1,-2,-3);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
	}

	//For the second predicate (Side1+Side2 <= Side3 || Side2+Side3 <= Side1 || Side1+Side3 <= Side2)
	//like the previous tests for the first predicate, in test 1 we test true values for each clause
	//and in test 2 we test the false values. 3 tests have been written to make each of them true.
	@ClauseCoverage(
		predicate = "d + e + f",
		valuations = {
			@Valuation(clause = 'd', valuation = true),
			@Valuation(clause = 'e', valuation = true),
			@Valuation(clause = 'f', valuation = true)
		}
	)
	@Test
	public void CC_SecondPredicate_Test1() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(1,2,4);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
		triClass = tryType.classifyTriangle(4,2,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
		triClass = tryType.classifyTriangle(1,4,2);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
	}

	//Here is a second test for clause coverage of second predicate. Here the false values for
	//clauses have been seen. In the one test below all the clauses will be false and clause coverage
	//will be completed.
	@ClauseCoverage(
		predicate = "d + e + f",
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		}
	)
	@Test
	public void CC_SecondPredicate_Test2() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(3,4,5);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.SCALENE, triClass);
	}

	//The first and second predicate are in form a + b + c so
	//For a : TFF would be UTP and FFF for NFP
	//For b : FTF would be UTP and FFF for NFP
	//For c : FFT would be UTP and FFF for NFP
	//So for CUTPNFP coverage for these two predicates we should test
	//{TFF, FTF, FFT, FFF} to satisfy the coverage.
	//Here is TFF for clause a of first predicate
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		clause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void CUTPNFP_FirstPredicate_Test1() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(-1,1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
	}

	//Here is FTF for clause b of first predicate
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		clause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void CUTPNFP_FirstPredicate_Test2() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(1,-1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
	}

	//Here is FFT for clause c of first predicate
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true)
		}
	)
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		clause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true)
		}
	)
	@Test
	public void CUTPNFP_FirstPredicate_Test3() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(1,1,-1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
	}

	//Here is FFF for clause a of first predicate (it is for clause b and c too)
	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		clause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void CUTPNFP_FirstPredicate_Test4() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(1,1,1);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.EQUILATERAL, triClass);
	}

	//Here is TFF for clause a of second predicate
	@UniqueTruePoint(
		predicate = "d + e + f",
		dnf = "d + e + f",
		implicant = "d",
		valuations = {
			@Valuation(clause = 'd', valuation = true),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		}
	)
	@NearFalsePoint(
		predicate = "d + e + f",
		dnf = "d + e + f",
		implicant = "d",
		clause = 'd',
		valuations = {
			@Valuation(clause = 'd', valuation = true),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		}
	)
	@Test
	public void CUTPNFP_SecondPredicate_Test1() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(1,2,4);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
	}

	//Here is FTF for clause b of second predicate
	@UniqueTruePoint(
		predicate = "d + e + f",
		dnf = "d + e + f",
		implicant = "e",
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = true),
			@Valuation(clause = 'f', valuation = false)
		}
	)
	@NearFalsePoint(
		predicate = "d + e + f",
		dnf = "d + e + f",
		implicant = "e",
		clause = 'e',
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = true),
			@Valuation(clause = 'f', valuation = false)
		}
	)
	@Test
	public void CUTPNFP_SecondPredicate_Test2() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(4,1,2);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
	}

	//Here is FFT for clause c of second predicate
	@UniqueTruePoint(
		predicate = "d + e + f",
		dnf = "d + e + f",
		implicant = "f",
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = true)
		}
	)
	@NearFalsePoint(
		predicate = "d + e + f",
		dnf = "d + e + f",
		implicant = "f",
		clause = 'f',
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = true)
		}
	)
	@Test
	public void CUTPNFP_SecondPredicate_Test3() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(1,4,2);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.NOT_VALID, triClass);
	}

	//Here is FFF for clause a of second predicate (it is for clause b and c too)
	@UniqueTruePoint(
		predicate = "d + e + f",
		dnf = "d + e + f",
		implicant = "d",
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		}
	)
	@NearFalsePoint(
		predicate = "d + e + f",
		dnf = "d + e + f",
		implicant = "d",
		clause = 'd',
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
			@Valuation(clause = 'f', valuation = false)
		}
	)
	@Test
	public void CUTPNFP_SecondPredicate_Test4() {
		TriType tryType = new TriType();
		TriType.TryClass triClass;
		triClass = tryType.classifyTriangle(3,4,5);
		log.debug("triangle identified as {}", triClass);
		Assertions.assertEquals(TriType.TryClass.SCALENE, triClass);
	}


	/**
	 f = ab + cd (example is in slides given in class)
	 -> UTP for ab = {TTFF, TTFT, TTTF}, UTP for cd = {FFTT, FTTT, TFTT}
	 -> clause a: UTP:TTFT -> NFP = FTFT
	 -> clause b: UTP:TTFT -> NFP = TFFT
	 -> clause c: UTP:TFTT -> NFP = TFFT
	 -> clause d: UTP:TFTT -> NFP = TFTF
	 -> CUTPNFP TRs = {TTFT, FTFT, TFFT, TFTT, TFTF}

	 ~f = ~a~c + ~a~d + ~b~c + ~b~d
	 CUTPNFP does not contain any UT for ~a~d which is an implicant for ~f and is needed for UTPC

	 -> CUTPNFP does not subsume UTPC

	 */
	private static boolean questionTwo(boolean a, boolean b, boolean c, boolean d, boolean e) {
		boolean predicate = false;
		predicate = (a & b) || (c & d);
		return predicate;
	}
}
