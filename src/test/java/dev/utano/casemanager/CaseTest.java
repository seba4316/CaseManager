package dev.utano.casemanager;

import dev.utano.formatter.DefaultFormatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CaseTest {

	public static void main(String[] args) {
		singularTest();
		completeTest();
	}

	public static void singularTest() {
		testConversion(Case.TITLE_CASE.getExample(), Case.TITLE_CASE, Case.LOWER_CASE, Case.LOWER_CASE.getExample());
	}

	private static void completeTest() {
		System.out.println("Testing case recognition");
		for (Case c : Case.values())
			if (c != Case.UNKNOWN)
				testRecognition(c.getExample(), c);

		System.out.println();
		System.out.println("Testing case conversion");

		for (Case c : Case.values())
			if (c != Case.UNKNOWN)
				for (Case to : Case.values())
					if (to != Case.UNKNOWN && c != to)
						testConversion(c.getExample(), c, to, to.getExample());
	}

	private static void testRecognition(String text, Case expected) {
		System.out.println(DefaultFormatter.format("Testing case %\nResult: %\nExpected: %\n-----------------", expected.name(), CaseManager.recogniseCase(text).name(), expected.name()));
	}

	private static void testConversion(String text, Case from, Case to, String expected) {
		System.out.println(DefaultFormatter.format("Converting % to %", from.name(), to.name()));
		String result = CaseManager.convert(text, to);
		boolean passed = result.equals(expected);
		System.out.println(DefaultFormatter.format("% -> % (Correct: %)", text, result, passed));
		if (!passed) {
			System.out.println("\nDID NOT PASS!\n");
			fail();
		}
		System.out.println("-------------------------------------------");
	}

	@Test
	public void testSingularConversion() {
		testConversion(Case.TITLE_CASE.getExample(), Case.TITLE_CASE, Case.LOWER_CASE, Case.LOWER_CASE.getExample());
	}

	@Test
	public void testCompleteRecognitionAndConversion() {
		// Testing case recognition
		for (Case c : Case.values()) {
			if (c != Case.UNKNOWN) {
				assertEquals(c, CaseManager.recogniseCase(c.getExample()), "Recognition failed for " + c.name());
			}
		}

		// Testing case conversion
		for (Case from : Case.values()) {
			if (from != Case.UNKNOWN) {
				for (Case to : Case.values()) {
					if (to != Case.UNKNOWN && from != to) {
						String result = CaseManager.convert(from.getExample(), to);
						assertEquals(to.getExample(), result, "Conversion failed from " + from.name() + " to " + to.name());
					}
				}
			}
		}
	}

	@Test
	public void testEmptyStringRecognition() {
		assertEquals(Case.UNKNOWN, CaseManager.recogniseCase(""), "Empty string should return UNKNOWN.");
	}

	@Test
	public void testWhitespaceOnlyRecognition() {
		assertEquals(Case.UNKNOWN, CaseManager.recogniseCase("   "), "Whitespace-only string should return UNKNOWN.");
	}

}