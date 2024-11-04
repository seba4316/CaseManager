package dev.utano.casemanager.converter;

import dev.utano.casemanager.Case;

public interface CaseConverter {

	String convertTo(String text, Case toCase);

}