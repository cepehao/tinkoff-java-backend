package edu.project1;

import edu.project1.dictionary.CDictionary;
import edu.project1.game.CGuessManager;
import edu.project1.game.CHangman;
import edu.project1.game.IConsoleGame;
import edu.project1.utils.CWord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Project1Test {

    @Test
    @DisplayName("Некорректный словарь выбрасывает исключение")
    void incorrectDictionary() {
        IConsoleGame hangman = new CHangman(new CDictionary(new String[] {"bad", "", "is"}));

        Assertions.assertThatThrownBy(hangman::run)
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Превышено количество попыток")
    void maxAttemptsReturnLose() {
        var guessManager = new CGuessManager(new CWord("test"));

        guessManager.newGuess("f");
        guessManager.newGuess("l");
        guessManager.newGuess("e");
        guessManager.newGuess("q");
        guessManager.newGuess("z");
        guessManager.newGuess("f");

        Assertions.assertThat(guessManager.isLost()).isEqualTo(true);
    }

    @Test
    @DisplayName("Выигрышная партия")
    void maxAttemptsReturnWon() {
        var guessManager = new CGuessManager(new CWord("victory"));

        guessManager.newGuess("v");
        guessManager.newGuess("y");
        guessManager.newGuess("v");
        guessManager.newGuess("i");
        guessManager.newGuess("c");
        guessManager.newGuess("t");
        guessManager.newGuess("t");
        guessManager.newGuess("o");
        guessManager.newGuess("z");
        guessManager.newGuess("r");

        Assertions.assertThat(guessManager.isWon()).isEqualTo(true);
    }

    @Test
    @DisplayName("Тестирование корректного состояния на угадывания букв")
    void correctState() {
        var guessManager = new CGuessManager(new CWord("correct"));

        guessManager.newGuess("v");
        guessManager.newGuess("y");
        guessManager.newGuess("c");
        guessManager.newGuess("t");
        Assertions.assertThat(guessManager.getWord().curState()).isEqualTo("c****ct");
        guessManager.newGuess("v");
        guessManager.newGuess("r");
        guessManager.newGuess("e");
        Assertions.assertThat(guessManager.getWord().curState()).isEqualTo("c*rrect");
    }

}
