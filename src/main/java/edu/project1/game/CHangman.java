package edu.project1.game;

import edu.project1.dictionary.CDictionary;
import edu.project1.session.CSession;
import edu.project1.utils.CWord;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class CHangman implements IConsoleGame {

    private final CDictionary dictionary;

    public CHangman() {
        this.dictionary = new CDictionary(getWords());
    }

    public CHangman(CDictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public void run() {
        var word = dictionary.randomWord();

        var session = new CSession(new CWord(word));

        session.startGame();
    }

    private String[] getWords() {
        Properties properties = new Properties();

        try (var fis = new FileInputStream("src/main/resources/project1/hangman.properties")) {
            properties.load(fis);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // Получаем значения по ключам и формируем массив строк
        List<String> values = new ArrayList<>();
        for (var value: properties.stringPropertyNames()) {
            values.add(properties.getProperty(value));
        }

        return values.toArray(new String[0]);
    }
}
