package dev.utano.casemanager;

import dev.utano.formatter.DefaultFormatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaseManagerTest {

	@Test
	public void testRecognition() {
		for (Case c : Case.values())
			if (c != Case.UNKNOWN)
				assertEquals(c, CaseManager.recogniseCase(c.getExample()));
	}

	@Test
	public void testConversion() {
		for (Case c : Case.values())
			if (c != Case.UNKNOWN)
				for (Case to : Case.values())
					if (to != Case.UNKNOWN && c != to) {
						String result = CaseManager.convert(c.getExample(), to);
						assertEquals(to.getExample(), result,
								DefaultFormatter.format("% (%) -> %", c.name(), c.getExample(), to.name()));
					}
	}

}