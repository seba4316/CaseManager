package dev.utano.casemanager;

import dev.utano.casemanager.converter.CaseConverter;
import dev.utano.casemanager.converter.CaseConverterImpl;
import dev.utano.casemanager.recogniser.CaseRecogniser;
import dev.utano.casemanager.recogniser.CaseRecogniserImpl;
import lombok.Setter;

public class CaseManager {

	@Setter
	private static CaseRecogniser caseRecogniser = new CaseRecogniserImpl();
	@Setter
	private static CaseConverter caseConverter = new CaseConverterImpl();

	public static Case recogniseCase(String text) {
		return caseRecogniser.recogniseCase(text);
	}

	public static String convert(String text, Case to) {
		return caseConverter.convertTo(text, to);
	}

}