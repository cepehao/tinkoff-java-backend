package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int PORT = 12345;
    private static final int MAX_CONNECTIONS = 3;
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(MAX_CONNECTIONS);

    @SuppressWarnings("RegexpSinglelineJava")
    public void start() {
        try (var serverSocket = new ServerSocket(PORT)) {
            LOGGER.info("Сервер запущен. Ожидание подключений...");

            while (true) {
                var clientSocket = serverSocket.accept();

                EXECUTOR_SERVICE.execute(new ClientHandler(clientSocket));
                LOGGER.info("Клиент подключен!");
            }
        } catch (IOException ex) {
            LOGGER.warn(ex.getStackTrace());
        }
    }
}
