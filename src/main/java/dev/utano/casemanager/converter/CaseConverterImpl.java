package dev.utano.casemanager.converter;

import dev.utano.casemanager.AnalysedString;
import dev.utano.casemanager.Case;
import dev.utano.casemanager.recogniser.CaseRecogniser;
import dev.utano.casemanager.recogniser.CaseRecogniserImpl;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CaseConverterImpl implements CaseConverter {

	private CaseRecogniser caseRecogniser;

	public CaseConverterImpl(CaseRecogniser caseRecogniser) {
		this.caseRecogniser = caseRecogniser;
	}

	public CaseConverterImpl() {
		this(new CaseRecogniserImpl());
	}

	@Override
	public String convertTo(String text, Case toCase) {
		Objects.requireNonNull(text, "An input string is required for conversion");
		Objects.requireNonNull(toCase, "A case is required for conversion");

		AnalysedString analysedString = caseRecogniser.analyseString(text);
		if (toCase == analysedString.getStringCase())
			return text;

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < analysedString.getTokens().length; i++) {
			String token = analysedString.getTokens()[i];
			toCase.getTokenConverter().accept(i, token, result);
		}
		return result.toString();
	}

}