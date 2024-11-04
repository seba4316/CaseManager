package dev.utano.casemanager;

import dev.utano.casemanager.recogniser.CaseRecogniser;
import dev.utano.casemanager.recogniser.CaseRecogniserImpl;
import dev.utano.casemanager.recogniser.NoRegexCaseRecogniser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaseRecogniserTest {

	@Test
	public void testRecognition() {
		CaseRecogniser caseRecogniser = new CaseRecogniserImpl();
		for (Case c : Case.values())
			if (c != Case.UNKNOWN)
				assertEquals(c, caseRecogniser.analyseString(c.getExample()).getStringCase());
	}

	@Test
	public void testRecognitionWithNoRegexRecogniser() {
		CaseRecogniser caseRecogniser = new NoRegexCaseRecogniser();
		AnalysedString analysed = caseRecogniser.analyseString("helloWorld");
		assertEquals(Case.CAMEL_CASE, analysed.getStringCase(), "NoRegexCaseRecogniser should recognize camelCase correctly.");

		analysed = caseRecogniser.analyseString("HelloWorld");
		assertEquals(Case.PASCAL_CASE, analysed.getStringCase(), "NoRegexCaseRecogniser should recognize PascalCase correctly.");

		analysed = caseRecogniser.analyseString("HELLO_WORLD");
		assertEquals(Case.MACRO_CASE, analysed.getStringCase(), "NoRegexCaseRecogniser should recognize MACRO_CASE correctly.");

		analysed = caseRecogniser.analyseString("hello_world");
		assertEquals(Case.SNAKE_CASE, analysed.getStringCase(), "NoRegexCaseRecogniser should recognize SNAKE_CASE correctly.");

		analysed = caseRecogniser.analyseString("HELLOWORLD");
		assertEquals(Case.UPPER_CASE, analysed.getStringCase(), "NoRegexCaseRecogniser should recognize UPPER_CASE correctly.");

		analysed = caseRecogniser.analyseString("helloworld");
		assertEquals(Case.LOWER_CASE, analysed.getStringCase(), "NoRegexCaseRecogniser should recognize LOWER_CASE correctly.");

		analysed = caseRecogniser.analyseString("Hello World");
		assertEquals(Case.TITLE_CASE, analysed.getStringCase(), "NoRegexCaseRecogniser should recognize TITLE_CASE correctly.");
	}

}