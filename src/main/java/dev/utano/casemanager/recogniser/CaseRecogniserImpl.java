package dev.utano.casemanager.recogniser;

import dev.utano.casemanager.AnalysedString;
import dev.utano.casemanager.Case;

public class CaseRecogniserImpl implements CaseRecogniser {

	@Override
	public AnalysedString analyseString(String text) {
		CaseRecoginser caseRecoginser = new CaseRecoginser();
		Case result = Case.UNKNOWN;

		if (text == null || text.trim().isEmpty())
			return new AnalysedString(Case.UNKNOWN, text, Case.UNKNOWN.tokenise(text), caseRecoginser);

		String trimmedText = text.trim();

		for (Case currentCase : Case.values()) {
			if (!caseRecoginser.isExcluded(currentCase) && currentCase.getPattern().matcher(trimmedText).matches()) {
				result = currentCase;
				break;
			}
		}

		// Handle UNKNOWN cases by defaulting to LOWER_CASE or UPPER_CASE
		if (result == Case.UNKNOWN) {
			if (trimmedText.equals(trimmedText.toLowerCase()))
				result = Case.LOWER_CASE;
			else if (trimmedText.equals(trimmedText.toUpperCase()))
				result = Case.UPPER_CASE;
		}

		return new AnalysedString(result, text, result.tokenise(text), caseRecoginser);
	}

}