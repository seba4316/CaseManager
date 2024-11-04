package dev.utano.casemanager.recogniser;

import dev.utano.casemanager.AnalysedString;
import dev.utano.casemanager.Case;

public class NoRegexCaseRecogniser implements CaseRecogniser {

	@Override
	public AnalysedString analyseString(String text) {
		CaseRecoginser caseRecoginser = new CaseRecoginser();
		Case result = Case.UNKNOWN;
		if (text == null || text.trim().length() < 3)
			return new AnalysedString(Case.UNKNOWN, text, Case.UNKNOWN.tokenise(text), caseRecoginser);

		String[] words = text.split(" ");

		if (!text.contains("_"))
			caseRecoginser.excludeCases(Case.MACRO_CASE, Case.SNAKE_CASE);

		if (words.length > 1)
			caseRecoginser.excludeCases(Case.CAMEL_CASE, Case.PASCAL_CASE, Case.SNAKE_CASE, Case.MACRO_CASE);

		boolean allUpperCase = true;
		boolean allLowerCase = true;
		boolean firstUpperRestLower = true;
		boolean allWordsStartWithUpperCase = true;
		boolean noSpaces = text.indexOf(' ') == -1;

		boolean isMacroCase = text.chars().allMatch(c -> Character.isUpperCase(c) || c == '_');

		for (String word : words) {
			char[] chars = word.toCharArray();

			if (chars.length > 0 && Character.isLowerCase(chars[0])) {
				firstUpperRestLower = false;
				allWordsStartWithUpperCase = false;
				caseRecoginser.excludeCases(Case.TITLE_CASE, Case.UPPER_CASE, Case.PASCAL_CASE, Case.MACRO_CASE);
			} else
				caseRecoginser.excludeCases(Case.LOWER_CASE, Case.SNAKE_CASE);

			boolean foundUpperCase = false;
			boolean foundLowerCaseAfterUpperCase = false;

			for (int i = 1; i < chars.length; i++) {
				char c = chars[i];
				if (c != '_' && !Character.isAlphabetic(c))
					continue;

				if (Character.isUpperCase(c)) {
					foundUpperCase = true;
					allLowerCase = false;
					caseRecoginser.excludeCases(Case.LOWER_CASE);
				} else {
					allUpperCase = false;
					caseRecoginser.excludeCases(Case.UPPER_CASE);

					if (foundUpperCase && !caseRecoginser.isExcluded(Case.CAMEL_CASE))
						foundLowerCaseAfterUpperCase = true;

					firstUpperRestLower = firstUpperRestLower && Character.isUpperCase(chars[0]) && Character.isLowerCase(c);
				}

				if (chars[i - 1] == '_') {
					if (Character.isUpperCase(c)) {
						if (!caseRecoginser.isExcluded(Case.MACRO_CASE))
							result = Case.MACRO_CASE;
					} else if (!caseRecoginser.isExcluded(Case.SNAKE_CASE))
						result = Case.SNAKE_CASE;
				}
			}

			if (allLowerCase)
				caseRecoginser.excludeCases(Case.CAMEL_CASE);

			if (Character.isLowerCase(chars[0]) && foundUpperCase && !caseRecoginser.isExcluded(Case.CAMEL_CASE)) {
				result = Case.CAMEL_CASE;
			} else if (Character.isUpperCase(chars[0]) && foundUpperCase && !foundLowerCaseAfterUpperCase && !caseRecoginser.isExcluded(Case.PASCAL_CASE)) {
				if (noSpaces)
					result = Case.PASCAL_CASE;
			}

			if (!(Character.isUpperCase(chars[0]) && firstUpperRestLower))
				allWordsStartWithUpperCase = false;
		}

		if (isMacroCase && !caseRecoginser.isExcluded(Case.MACRO_CASE))
			result = Case.MACRO_CASE;
		else if (allUpperCase && !caseRecoginser.isExcluded(Case.UPPER_CASE))
			result = Case.UPPER_CASE;
		else if (allWordsStartWithUpperCase && !caseRecoginser.isExcluded(Case.TITLE_CASE) && !noSpaces)
			result = Case.TITLE_CASE;
		else if (noSpaces && !caseRecoginser.isExcluded(Case.PASCAL_CASE))
			result = Case.PASCAL_CASE;

		if (!caseRecoginser.isExcluded(Case.PASCAL_CASE) && result == Case.UNKNOWN)
			return new AnalysedString(Case.PASCAL_CASE, text, Case.PASCAL_CASE.tokenise(text), caseRecoginser);
		if (!caseRecoginser.isExcluded(Case.CAMEL_CASE) && result == Case.UNKNOWN)
			return new AnalysedString(Case.CAMEL_CASE, text, Case.CAMEL_CASE.tokenise(text), caseRecoginser);
		if (!caseRecoginser.isExcluded(Case.TITLE_CASE) && result == Case.UNKNOWN)
			result = Case.TITLE_CASE;
		if (!caseRecoginser.isExcluded(Case.LOWER_CASE) && result == Case.UNKNOWN)
			result = Case.LOWER_CASE;
		if (!caseRecoginser.isExcluded(Case.UPPER_CASE) && result == Case.UNKNOWN)
			result = Case.UPPER_CASE;

		return new AnalysedString(result, text, result.tokenise(text), caseRecoginser);
	}

}