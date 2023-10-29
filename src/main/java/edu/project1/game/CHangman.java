package edu.project1.game;

import edu.project1.dictionary.CDictionary;
import edu.project1.session.CSession;
import edu.project1.utils.CWord;

public final class CHangman implements IConsoleGame {

    private final CDictionary dictionary;

    public CHangman() {
        this.dictionary = new CDictionary(new String[] {"chair", "headphones", "notebook", "mouse", "pencil"});
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
}
