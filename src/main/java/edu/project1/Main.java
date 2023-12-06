package edu.project1;

import edu.project1.game.CHangman;
import edu.project1.game.IConsoleGame;

public final class Main {
    private Main() {

    }

    public static void main(String[] args) {
        IConsoleGame hangman = new CHangman();

        hangman.run();
    }
}
