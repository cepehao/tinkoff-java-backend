package edu.project1.session;

import edu.project1.game.CGuessManager;
import edu.project1.utils.CWord;
import static edu.project1.utils.IOModule.LOGGER;
import static edu.project1.utils.IOModule.SCANNER;

public final class CSession {
    private final CWord word;
    private static final int POSSIBLE_MAX_ATTEMPT = 4;

    public CSession(CWord word) {
        this.word = word;
    }

    public void startGame() {
        var guessManager = new CGuessManager(word);

        if (guessManager.getMaxAttempts() <= POSSIBLE_MAX_ATTEMPT) {
            LOGGER.error("The word must have a minimum of four letters");
            throw new IllegalArgumentException();
        }

        LOGGER.info("Game on. For give up enter \"exit\"");

        while (true) {
            LOGGER.info("Guess a letter:");

            var playerInput = SCANNER.nextLine();

            guessManager.newGuess(playerInput);

            if (guessManager.isWon()) {
                LOGGER.info("You won!");
                break;
            }

            if (guessManager.isLost()) {
                LOGGER.info("You lost!");
                break;
            }
        }
    }

}
