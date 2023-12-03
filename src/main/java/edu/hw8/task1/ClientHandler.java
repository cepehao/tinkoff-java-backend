package edu.hw8.task1;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ClientHandler implements Runnable {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final int BUFF_SIZE = 1024;
    private static final String DEFAULT_ANSWER = "На такой глупый вопрос ответ еще не придумали";

    private static final Map<String, String> DICTIONARY = new HashMap<>();

    static {
        DICTIONARY.put("личности", "Не переходи на личности там, где их нет");
        DICTIONARY.put("оскорбления", "Если твои противники перешли на личные оскорбления, "
            + "будь уверена — твоя победа не за горами");
        DICTIONARY.put("глупый", "А я тебе говорил, что ты глупый? "
            + "Так вот, я забираю свои слова обратно... Ты просто бог идиотизма");
        DICTIONARY.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    private final Socket clientSocket;

    ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
            var inputStream = clientSocket.getInputStream();
            var outputStream = clientSocket.getOutputStream()
        ) {
            var buffer = new byte[BUFF_SIZE];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                var request = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                var response = getResponse(request);
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException ex) {
            LOGGER.warn(ex.getStackTrace());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ex) {
                LOGGER.warn(ex.getStackTrace());
            }
        }
    }

    private String getResponse(String request) {
        return DICTIONARY.getOrDefault(request.toLowerCase(), DEFAULT_ANSWER);
    }
}
