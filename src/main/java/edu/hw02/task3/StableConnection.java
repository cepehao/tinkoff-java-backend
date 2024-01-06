package edu.hw02.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Стабильное соединение работает всегда
public final class StableConnection implements Connection {

    private static final Logger LOGGER = LogManager.getLogger();

    private String executedMessage(String command) {
        return "Команда \"" + command + "\" успешно выполнена";
    }

    private String connectionClosedMessage() {
        return "Соединение закрыто";
    }

    @Override
    public void execute(String command) {
        LOGGER.info(executedMessage(command));
    }

    @Override
    public void close() {
        LOGGER.info(connectionClosedMessage());
    }
}
