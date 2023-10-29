package edu.project1.game;

import edu.project1.utils.CWord;
import static edu.project1.utils.IOModule.LOGGER;
import static edu.project1.utils.IOModule.PATTERN;

public final class CGuessManager {
    private final CWord word;
    private final int maxAttempts;
    private int curAttempt;

    @SuppressWarnings("MagicNumber")
    public CGuessManager(CWord word) {
        this.word = word;
        this.maxAttempts = word.getLength() / 2 + 3;
        this.curAttempt = 0;
    }

    public CWord getWord() {
        return word;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void newGuess(String playerInput) {
        var matcher = PATTERN.matcher(playerInput);

        if (playerInput.equals("exit")) {
            LOGGER.info("We look forward to seeing you again.");
            System.exit(0);
        } else if (!matcher.matches()) {
            LOGGER.warn("The input must consist of 1 character of the Latin alphabet");
        } else if (word.alreadyInput(playerInput)) {
            LOGGER.warn("You have already entered " + playerInput + " before");
        } else if (word.isSubstring(playerInput.charAt(0))) {
            LOGGER.info("Hit!");
            LOGGER.info("The word: " + word.curState());
        }

        LOGGER.info("Missed, mistake " + ++curAttempt + " out of " + maxAttempts);
    }

    public boolean isWon() {
        return word.allOpen();
    }

    public boolean isLost() {
        return curAttempt >= maxAttempts;
    }
}
