package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Проблемное соединение с некоторой вероятностью бросает исключение
public final class FaultyConnection implements Connection {

    private static final Random RANDOM = new Random();
    private static final Logger LOGGER = LogManager.getLogger();

    private String executedMessage(String command) {
        return "Команда \"" + command + "\" успешно выполнена";
    }

    private String connectionClosedMessage() {
        return "Соединение закрыто";
    }

    private String errorMessage() {
        return "Ошибка во время исполнения команды";
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public void execute(String command) {
        // Генерация рандомного числа в интервале [-10;10]
        int randomValue = RANDOM.nextInt(21) - 10;

        // На нулевое значение бросаем ArithmeticException
        if (randomValue == 0) {
            LOGGER.info(errorMessage());

            throw new ArithmeticException("Нулевое значение");
        }

        // На четные числа бросаем IllegalArgumentException
        if (randomValue % 2 == 0) {
            LOGGER.info(errorMessage());

            throw new IllegalArgumentException("Четное число");
        }

        LOGGER.info(executedMessage(command));

    }

    @Override
    public void close() {
        LOGGER.info(connectionClosedMessage());
    }
}
