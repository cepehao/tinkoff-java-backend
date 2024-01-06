package edu.hw02.task3;

public final class PopularCommandExecutor {
    private static final int COUNT_TRY = 1000;

    private final ConnectionManager manager;
    private final int maxAttempts;
    private int count = 0;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() throws Exception {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) throws Exception {
        for (int i = 0; i < COUNT_TRY; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
            } catch (ArithmeticException | IllegalArgumentException exception) {
                count++;

                if (count >= maxAttempts) {
                    // Пользуемся конструктором с двумя аргументами
                    // public RuntimeException(String message, Throwable cause)
                    throw new ConnectionException(exception.getMessage(), exception);
                }
            }
        }
    }
}
