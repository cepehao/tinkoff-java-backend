package edu.hw6.task5;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
//import com.google.gson.Gson;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.time.Duration;
//import java.time.temporal.ChronoUnit;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;

class HackerNewsTest {

    private HackerNews hackerNews;

    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final int TIME_LIMIT = 10;

    @BeforeEach
    void setUp() {
        hackerNews = new HackerNews();
    }

    @SuppressWarnings("MagicNumber")
    private static Stream<Arguments> getNews() {
        return Stream.of(
            Arguments.of(37570037, "JDK 21 Release Notes"),
            Arguments.of(38276209, "Don't Spy EU"),
            Arguments.of(38266912, "Show HN: GPT-4-Vision UX audit for your landing page (relaunch)"),
            Arguments.of(38269175, "Did Reddit just denylist all IPs?")
        );
    }

//    @Test
//    void hackerNewsTopStories() {
//        var actual = hackerNews.hackerNewsTopStories();
//
//        var gson = new Gson();
//        var actualJSON = gson.toJson(actual);
//
//        try (var client = HttpClient.newHttpClient()) {
//
//            var request = HttpRequest.newBuilder()
//                .uri(new URI(TOP_STORIES_URL))
//                .GET()
//                .header("AcceptEncoding", "gzip")
//                .timeout(Duration.of(TIME_LIMIT, ChronoUnit.SECONDS))
//                .build();
//
//            var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
//
//            Assertions.assertThat(actualJSON).isEqualTo(response);
//        } catch (Exception ignored) {
//
//        }
//
//    }
//
//    @ParameterizedTest
//    @MethodSource("getNews")
//    void news(long id, String expectedTitle) {
//        var actual = hackerNews.news(id);
//
//        Assertions.assertThat(actual).isEqualTo(expectedTitle);
//
//    }
}
