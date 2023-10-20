package edu.hw2.task3;

// С некоторой вероятностью возвращает проблемное соединение
public final class DefaultConnectionManager implements ConnectionManager {
    private final int connectionId;

    public DefaultConnectionManager(int connectionId) {
        this.connectionId = connectionId;
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public Connection getConnection() {
        // Если рандомное значение < 0 -> возвращаем проблемное соединение
        if (connectionId < 0) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
