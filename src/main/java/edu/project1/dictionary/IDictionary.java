package edu.project1.dictionary;

public sealed interface IDictionary permits CDictionary {
    String randomWord();
}
