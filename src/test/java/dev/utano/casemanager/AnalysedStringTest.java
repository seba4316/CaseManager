package dev.utano.casemanager;

import dev.utano.casemanager.recogniser.CaseRecoginser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnalysedStringTest {

    @Test
    public void testAnalysedStringCreation() {
        String text = "helloWorld";
        AnalysedString analysed = new AnalysedString(Case.CAMEL_CASE, text, new String[]{"hello", "World"}, new CaseRecoginser());
        
        assertEquals(Case.CAMEL_CASE, analysed.getStringCase(), "String case should be CAMEL_CASE.");
        assertEquals(text, analysed.getString(), "String should match the input text.");
        assertArrayEquals(new String[]{"hello", "World"}, analysed.getTokens(), "Tokens should match the expected tokens.");
        assertNotNull(analysed.getCaseRecoginser(), "CaseRecogniser should not be null.");
    }

    @Test
    public void testAnalysedStringWithUnknownCase() {
        String text = "hello@world";
        AnalysedString analysed = new AnalysedString(Case.UNKNOWN, text, new String[]{}, new CaseRecoginser());
        
        assertEquals(Case.UNKNOWN, analysed.getStringCase(), "String case should be UNKNOWN.");
        assertEquals(text, analysed.getString(), "String should match the input text.");
        assertEquals(0, analysed.getTokens().length, "Tokens should be empty for UNKNOWN case.");
    }
}
