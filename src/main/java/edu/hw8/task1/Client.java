package edu.hw8.task1;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static final int BUFF_SIZE = 1024;

    @SuppressWarnings("RegexpSinglelineJava")
    public void connect() {
        try (var socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             var inputStream = socket.getInputStream();
             var outputStream = socket.getOutputStream();
             var scanner = new Scanner(System.in)) {

            while (true) {
                System.out.print("Клиент: ");
                var input = scanner.nextLine();
                var inputBytes = input.getBytes(StandardCharsets.UTF_8);

                outputStream.write(inputBytes);

                var buffer = new byte[BUFF_SIZE];
                var bytesRead = inputStream.read(buffer);
                var response = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);

                System.out.println("Сервер: " + response);
            }

        } catch (IOException ex) {
            LOGGER.warn(ex.getStackTrace());
        }
    }

//    public static void main(String[] args) {
//        var client = new Client();
//        client.connect();
//    }
}
