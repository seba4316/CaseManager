package dev.utano.casemanager.recogniser;

import dev.utano.casemanager.Case;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaseRecoginser {

	private final List<Case> excludedCases = new ArrayList<>();

	public void excludeCases(Case... cases) {
		excludedCases.addAll(Arrays.asList(cases));
	}

	public boolean isExcluded(Case check) {
		return excludedCases.contains(check);
	}

	public List<Case> getValidCases() {
		List<Case> validCases = new ArrayList<>();
		for (Case c : Case.values())
			if (!isExcluded(c))
				validCases.add(c);
		return validCases;
	}

}