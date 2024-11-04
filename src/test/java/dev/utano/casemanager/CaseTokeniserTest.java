package dev.utano.casemanager;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CaseTokeniserTest {

	@Test
	public void testTokeniseUnknownCase() {
		String text = "hello@world";
		String[] tokens = Case.UNKNOWN.tokenise(text);
		assertNotNull(tokens, "Tokens should not be null.");
		assertEquals(0, tokens.length, "UNKNOWN case should return no tokens.");
	}

	@Test
	public void testTokenisePascalCaseWithNumbers() {
		String text = "HelloWorld123";
		String[] tokens = Case.PASCAL_CASE.tokenise(text);
		assertNotNull(tokens, "Tokens should not be null.");
		assertEquals(3, tokens.length, "PascalCase with numbers should return three tokens.");
		assertEquals("Hello", tokens[0], "First token should be 'Hello'.");
		assertEquals("World", tokens[1], "Second token should be 'World'.");
		assertEquals("123", tokens[2], "Second token should be '123'.");
	}

	@Test
	public void testTokeniseCamelCaseWithNumbers() {
		String text = "helloWorld123";
		String[] tokens = Case.CAMEL_CASE.tokenise(text);
		assertNotNull(tokens, "Tokens should not be null.");
		assertEquals(3, tokens.length, "camelCase with numbers should return three tokens.");
		assertEquals("hello", tokens[0], "First token should be 'hello'.");
		assertEquals("World", tokens[1], "Second token should be 'World'.");
		assertEquals("123", tokens[2], "Third token should be '123'.");
	}

	@Test
	public void testTokeniseTitleCaseWithSingleWord() {
		String text = "Hello";
		String[] tokens = Case.TITLE_CASE.tokenise(text);
		assertNotNull(tokens, "Tokens should not be null.");
		assertEquals(1, tokens.length, "Title case with single word should return one token.");
		assertEquals("Hello", tokens[0], "Token should match the input word.");
	}

	@Test
	public void testTokeniseMacroCaseWithNumbers() {
		String text = "HELLO_WORLD_123";
		String[] tokens = Case.MACRO_CASE.tokenise(text);
		assertNotNull(tokens, "Tokens should not be null.");
		assertEquals(3, tokens.length, "MACRO_CASE with numbers should return three tokens.");
		assertEquals("HELLO", tokens[0], "First token should be 'HELLO'.");
		assertEquals("WORLD", tokens[1], "Second token should be 'WORLD'.");
		assertEquals("123", tokens[2], "Third token should be '123'.");
	}

	@Test
	public void testTokeniseSnakeCaseWithTrailingUnderscores() {
		String text = "hello_world__";
		String[] tokens = Case.SNAKE_CASE.tokenise(text);
		assertNotNull(tokens, "Tokens should not be null.");
		assertEquals(2, tokens.length, "SNAKE_CASE with trailing underscores should return two tokens.");
		assertEquals("hello", tokens[0], "First token should be 'hello'.");
		assertEquals("world", tokens[1], "Second token should be 'world'.");
	}

	@Test
	public void titleCase() {
		String text = "Hello My World";

		String token1 = "Hello";
		String token2 = "My";
		String token3 = "World";
		String[] tokens = Case.TITLE_CASE.tokenise(text);
		assert tokens != null;
		assertEquals(3, tokens.length);
		assertEquals(token1, tokens[0]);
		assertEquals(token2, tokens[1]);
		assertEquals(token3, tokens[2]);
	}

	@Test
	public void upperCase() {
		String text = "HELLO MY WORLD";

		String token1 = "HELLO";
		String token2 = "MY";
		String token3 = "WORLD";
		String[] tokens = Case.UPPER_CASE.tokenise(text);
		assert tokens != null;
		assertEquals(3, tokens.length);
		assertEquals(token1, tokens[0]);
		assertEquals(token2, tokens[1]);
		assertEquals(token3, tokens[2]);
	}

	@Test
	public void lowerCase() {
		String text = "hello my world";

		String token1 = "hello";
		String token2 = "my";
		String token3 = "world";
		String[] tokens = Case.LOWER_CASE.tokenise(text);
		assert tokens != null;
		assertEquals(3, tokens.length);
		assertEquals(token1, tokens[0]);
		assertEquals(token2, tokens[1]);
		assertEquals(token3, tokens[2]);
	}

	@Test
	public void camelCase() {
		String text = "helloMyWorld";

		String token1 = "hello";
		String token2 = "My";
		String token3 = "World";
		String[] tokens = Case.CAMEL_CASE.tokenise(text);
		assert tokens != null;
		assertEquals(3, tokens.length);
		assertEquals(token1, tokens[0]);
		assertEquals(token2, tokens[1]);
		assertEquals(token3, tokens[2]);
	}

	@Test
	public void pascalCase() {
		String text = "HelloMyWorld";

		String token1 = "Hello";
		String token2 = "My";
		String token3 = "World";
		String[] tokens = Case.PASCAL_CASE.tokenise(text);
		assert tokens != null;
		assertEquals(3, tokens.length);
		assertEquals(token1, tokens[0]);
		assertEquals(token2, tokens[1]);
		assertEquals(token3, tokens[2]);
	}

	@Test
	public void macroCase() {
		String text = "HELLO_MY_WORLD";

		String token1 = "HELLO";
		String token2 = "MY";
		String token3 = "WORLD";
		String[] tokens = Case.MACRO_CASE.tokenise(text);
		assert tokens != null;
		assertEquals(3, tokens.length);
		assertEquals(token1, tokens[0]);
		assertEquals(token2, tokens[1]);
		assertEquals(token3, tokens[2]);
	}

	@Test
	public void snakeCase() {
		String text = "hello_my_world";

		String token1 = "hello";
		String token2 = "my";
		String token3 = "world";
		String[] tokens = Case.SNAKE_CASE.tokenise(text);
		assert tokens != null;
		assertEquals(3, tokens.length);
		assertEquals(token1, tokens[0]);
		assertEquals(token2, tokens[1]);
		assertEquals(token3, tokens[2]);
	}

	@Test
	public void multipleSpaces() {
		String text = "Hello  My World";

		String token1 = "Hello";
		String token2 = "My";
		String token3 = "World";
		String[] tokens = Case.TITLE_CASE.tokenise(text);
		assert tokens != null;
		assertEquals(3, tokens.length);
		assertEquals(token1, tokens[0]);
		assertEquals(token2, tokens[1]);
		assertEquals(token3, tokens[2]);
	}

	@Test
	public void multipleUnderscores() {
		String text = "hello__my_world";

		String token1 = "hello";
		String token2 = "my";
		String token3 = "world";
		String[] tokens = Case.SNAKE_CASE.tokenise(text);
		assert tokens != null;
		assertEquals(3, tokens.length);
		assertEquals(token1, tokens[0]);
		assertEquals(token2, tokens[1]);
		assertEquals(token3, tokens[2]);
	}

}