package dev.utano.casemanager;

import dev.utano.casemanager.recogniser.CaseRecoginser;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnalysedString {

	private final Case stringCase;
	private final String string;
	private final String[] tokens;
	private CaseRecoginser caseRecoginser;

}