package dev.utano.casemanager;

import dev.utano.casemanager.converter.CaseConverter;
import dev.utano.casemanager.converter.CaseConverterImpl;
import dev.utano.formatter.DefaultFormatter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CaseConverterTest {

	@Test
	public void testConversion() {
		CaseConverter caseConverter = new CaseConverterImpl();
		Set<Case> cases = Arrays.stream(Case.values())
				.filter(aCase -> aCase != Case.UNKNOWN)
				.collect(Collectors.toSet()); // Excluding UNKNOWN case
		for (Case c : cases) {
			for (Case to : cases) {
				if (c == to)
					continue;
				String result = caseConverter.convertTo(c.getExample(), to);
				assertEquals(to.getExample(), result,
						DefaultFormatter.format("% (%) -> %", c.name(), c.getExample(), to.name()));
			}
		}
	}

	@Test
	public void manualTest() {
		CaseConverter converter = new CaseConverterImpl();
		String result = converter.convertTo("Hello World", Case.MACRO_CASE); // HELLO_WORLD
		assertEquals("HELLO_WORLD", result);
		result = converter.convertTo(result, Case.CAMEL_CASE); // helloWorld
	}

	@Test
	public void testConversionToUnknownCase() {
		CaseConverter caseConverter = new CaseConverterImpl();
		String text = "helloWorld";
		String result = caseConverter.convertTo(text, Case.UNKNOWN);
		assertEquals(text, result, "Converting to UNKNOWN case should return the original string.");
	}

	@Test
	public void testConversionWithNullInput() {
		CaseConverter caseConverter = new CaseConverterImpl();
		assertThrows(NullPointerException.class, () -> {
			caseConverter.convertTo(null, Case.CAMEL_CASE);
		}, "Converting null should throw NullPointerException.");
	}

	@Test
	public void testConversionWithUnsupportedCase() {
		CaseConverter caseConverter = new CaseConverterImpl();
		String text = "helloWorld";
		assertThrows(NullPointerException.class, () -> {
			caseConverter.convertTo(text, null);
		}, "Converting to a null case should throw NullPointerException.");
	}

	@Test
	public void testConversionWithSpecialCharacters() {
		CaseConverter caseConverter = new CaseConverterImpl();
		String text = "hello@world";
		String result = caseConverter.convertTo(text, Case.SNAKE_CASE);
		assertEquals("hello@world", result, "Converting a string with special characters should return the original string.");
	}

	@Test
	public void testConversionWithSingleCharacter() {
		CaseConverter caseConverter = new CaseConverterImpl();
		String resultLower = caseConverter.convertTo("A", Case.LOWER_CASE);
		assertEquals("a", resultLower, "Converting single uppercase character to LOWER_CASE should return lowercase.");

		String resultUpper = caseConverter.convertTo("a", Case.UPPER_CASE);
		assertEquals("A", resultUpper, "Converting single lowercase character to UPPER_CASE should return uppercase.");
	}

}