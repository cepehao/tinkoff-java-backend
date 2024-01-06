package edu.hw06.task5;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HackerNews {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String MESSAGE_INFO_URL = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final String NEWS_TITLE_REGEX = "\"title\":\"([^\"]*)\"";
    private static final String ACCEPT_ENCODING = "AcceptEncoding";
    private static final String GZIP = "gzip";
    private static final String CLIENT_ERROR_MESSAGE = "Ошибка во время создания клиента";
    private static final String SEND_ERROR_MESSAGE = "Ошибка во время отправки запроса на сервер";
    private static final String REQUEST_ERROR_MESSAGE = "Ошибка во время получения ответа от сервера";
    private static final String UNDEFINED_NEWS = "Undefined news";
    private static final int TIME_LIMIT = 10;

    public long[] hackerNewsTopStories() {
        try (var client = HttpClient.newHttpClient()) {
            var request = HttpRequest.newBuilder()
                .uri(new URI(TOP_STORIES_URL))
                .GET()
                .header(ACCEPT_ENCODING, GZIP)
                .timeout(Duration.of(TIME_LIMIT, ChronoUnit.SECONDS))
                .build();

            return Arrays.stream(client.send(request, HttpResponse.BodyHandlers.ofString())
                    .body()
                    .replace("[", "").replace("]", "")
                    .split(","))
                .mapToLong(Long::valueOf)
                .toArray();

        } catch (UncheckedIOException ex) {
            LOGGER.warn(CLIENT_ERROR_MESSAGE + ex.getMessage());
            return new long[0];
        } catch (URISyntaxException ex) {
            LOGGER.warn(REQUEST_ERROR_MESSAGE + ex.getMessage());
            return new long[0];
        } catch (InterruptedException | IOException ex) {
            LOGGER.warn(SEND_ERROR_MESSAGE + ex.getMessage());
            return new long[0];
        }
    }

    public String news(long id) {
        String title;

        try (var client = HttpClient.newHttpClient()) {
            var request = HttpRequest.newBuilder()
                .uri(new URI(MESSAGE_INFO_URL.formatted(id)))
                .GET()
                .header(ACCEPT_ENCODING, GZIP)
                .timeout(Duration.of(TIME_LIMIT, ChronoUnit.SECONDS))
                .build();

            var body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            var pattern = Pattern.compile(NEWS_TITLE_REGEX);
            var mather = pattern.matcher(body);

            if (mather.find()) {
                title = mather.group(1);
            } else {
                LOGGER.warn("Заголовок не найден");
                title = UNDEFINED_NEWS;
            }
        } catch (UncheckedIOException ex) {
            LOGGER.warn(CLIENT_ERROR_MESSAGE + ex.getMessage());
            title = UNDEFINED_NEWS;
        } catch (URISyntaxException ex) {
            LOGGER.warn(REQUEST_ERROR_MESSAGE + ex.getMessage());
            title = UNDEFINED_NEWS;
        } catch (InterruptedException | IOException ex) {
            LOGGER.warn(SEND_ERROR_MESSAGE + ex.getMessage());
            title = UNDEFINED_NEWS;
        }
        return title;
    }
}
