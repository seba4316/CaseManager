package dev.utano.casemanager.recogniser;

import dev.utano.casemanager.AnalysedString;
import dev.utano.casemanager.Case;

public interface CaseRecogniser {

	AnalysedString analyseString(String text);

	default Case recogniseCase(String text) {
		return analyseString(text).getStringCase();
	}

}