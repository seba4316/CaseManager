package dev.utano.casemanager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.wavelength.betterreflection.EnumBetterReflectionClass;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
public enum Case {

	TITLE_CASE("Example Phrase", Pattern.compile("^[A-Z][a-z0-9]+(?: [A-Z][a-z0-9]+)*$"), (index, token, builder) -> {
		if (index > 0)
			builder.append(' ');
		builder.append(Character.toUpperCase(token.charAt(0))).append(token.substring(1).toLowerCase());
	}),
	LOWER_CASE("example phrase", Pattern.compile("^[a-z0-9]+$"), (index, token, builder) -> {
		if (index > 0)
			builder.append(' ');
		builder.append(token.toLowerCase());
	}),
	UPPER_CASE("EXAMPLE PHRASE", Pattern.compile("^[A-Z0-9]+$"), (index, token, builder) -> {
		if (index > 0)
			builder.append(' ');
		builder.append(token.toUpperCase());
	}),
	CAMEL_CASE("examplePhrase", Pattern.compile("^[a-z]+([A-Z][a-z0-9]+)*$"), (index, token, builder) -> {
		char c = token.charAt(0);
		if (index > 0)
			c = Character.toUpperCase(c);
		else
			c = Character.toLowerCase(c);
		builder.append(c).append(token.substring(1).toLowerCase());
	}),
	PASCAL_CASE("ExamplePhrase", Pattern.compile("^[A-Z][a-z0-9]+([A-Z][a-z0-9]+)*$"), (index, token, builder) -> {
		char c = token.charAt(0);
		c = Character.toUpperCase(c);
		builder.append(c).append(token.substring(1).toLowerCase());
	}),
	MACRO_CASE("EXAMPLE_PHRASE", Pattern.compile("^[A-Z0-9]+(_[A-Z0-9]+)*$"), (index, token, builder) -> {
		if (index > 0)
			builder.append('_');
		token = token.toUpperCase();
		builder.append(token);
	}),
	SNAKE_CASE("example_phrase", Pattern.compile("^[a-z0-9]+(_[a-z0-9]+)*$"), (index, token, builder) -> {
		if (index > 0)
			builder.append('_');
		token = token.toLowerCase();
		builder.append(token);
	}),
	UNKNOWN("", Pattern.compile(".*"), (index, token, builder) -> {
		builder.append(token);
	});

	private final String example;
	private final Pattern pattern;
	private final TokenConverter tokenConverter;

	public String[] tokenise(String text) {
		switch (this) {
		case TITLE_CASE:
		case LOWER_CASE:
		case UPPER_CASE:
			return text.split("\\s+");
		case CAMEL_CASE:
		case PASCAL_CASE:
			int start = 0;
			int length = 0;
			List<String> tokens = new ArrayList<>();
			boolean wasNumber = false;
			for (char c : text.toCharArray()) {
				if ((Character.isUpperCase(c) && (start != 0 || length != 0))
						|| Character.isDigit(c) != wasNumber) {
					tokens.add(text.substring(start, start + length));
					start += length;
					length = 1;
					wasNumber = Character.isDigit(c);
					continue;
				}
				length++;
			}
			if (length != 0) // Adding what's left
				tokens.add(text.substring(start));
			return tokens.toArray(new String[0]);
		case MACRO_CASE:
		case SNAKE_CASE:
			return text.split("_+");
		case UNKNOWN:
			break;
		}
		return new String[0];
	}

	public static final EnumBetterReflectionClass<Case> CLASS = new EnumBetterReflectionClass<>(Case.class);

}