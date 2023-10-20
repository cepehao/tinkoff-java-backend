package edu.hw2;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.ConnectionManager;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task3Test {

    private ConnectionManager connectionManager;
    private PopularCommandExecutor popularCommandExecutor;
    private static final int MAX_ATTEMPTS = 3;

    @Test
    @DisplayName("Проблемный менеджер всегда выбрасывает исключение")
    void testWithFaultyConnectionManager() {
        connectionManager = new FaultyConnectionManager();
        popularCommandExecutor = new PopularCommandExecutor(connectionManager, MAX_ATTEMPTS);

        Assertions.assertThatThrownBy(() -> popularCommandExecutor.updatePackages())
            .isInstanceOf(ConnectionException.class);
    }

    @Test
    @DisplayName("Дефолтный менеджер не обязательно выбрасывает исключение")
    void testWithDefaultConnectionManagerDoesntThrowException() {
        connectionManager = new DefaultConnectionManager(1);
        popularCommandExecutor = new PopularCommandExecutor(connectionManager, MAX_ATTEMPTS);

        Assertions.assertThatCode(() -> popularCommandExecutor.updatePackages()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Дефолтный менеджер может выбрасывать исключение")
    void testWithDefaultConnectionManagerThrowException() {
        connectionManager = new DefaultConnectionManager(-1);
        popularCommandExecutor = new PopularCommandExecutor(connectionManager, MAX_ATTEMPTS);

        Assertions.assertThatThrownBy(() -> popularCommandExecutor.updatePackages())
            .isInstanceOf(ConnectionException.class);
    }

}
