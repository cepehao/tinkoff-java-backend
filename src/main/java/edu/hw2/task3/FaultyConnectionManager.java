package edu.hw2.task3;

// Всегда возвращает проблемное соединение
public final class FaultyConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        return new FaultyConnection();
    }
}
