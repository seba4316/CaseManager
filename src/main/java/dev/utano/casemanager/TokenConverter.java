package dev.utano.casemanager;

@FunctionalInterface
public interface TokenConverter {
	void accept(Integer index, String token, StringBuilder result);

}