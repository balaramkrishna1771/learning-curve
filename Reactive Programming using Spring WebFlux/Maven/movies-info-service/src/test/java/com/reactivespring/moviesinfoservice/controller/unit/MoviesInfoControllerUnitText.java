package com.reactivespring.moviesinfoservice.controller.unit;

import com.reactivespring.moviesinfoservice.controller.MovieInfoController;
import com.reactivespring.moviesinfoservice.domain.MovieInfo;
import com.reactivespring.moviesinfoservice.service.MovieInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;


@WebFluxTest(controllers = MovieInfoController.class)
@AutoConfigureWebTestClient
public class MoviesInfoControllerUnitText {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MovieInfoService movieInfoServiceMock;

    static  String MOVIE_INFO_URL = "/v1/movieinfos";

    @Test
    void getMovieInfos(){

        var movieinfos = List.of(new MovieInfo(null, "Batman Begins",
                        2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")),
                new MovieInfo(null, "The Dark Knight",
                        2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18")),
                new MovieInfo("abc", "Dark Knight Rises",
                        2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-20")));


        when(movieInfoServiceMock.getAllMovies()).thenReturn(Flux.fromIterable(movieinfos).log());

        webTestClient.get()
                .uri(MOVIE_INFO_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(MovieInfo.class)
                .hasSize(3);
    }

    @Test
    void getMovieByID() {

        var recordByID = new MovieInfo("abc", "Dark Knight Rises",
                2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-20"));

        when(movieInfoServiceMock.getMovieByID("abc")).thenReturn(Mono.just(recordByID));

        webTestClient.get()
                .uri(MOVIE_INFO_URL + "/abc")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(MovieInfo.class)
                .consumeWith(InfoEntityExchangeResult -> {
                    var saveMovieInfo = InfoEntityExchangeResult.getResponseBody();
                    assertNotEquals(null, saveMovieInfo);
                    assert saveMovieInfo != null;
                    assertEquals("Dark Knight Rises", saveMovieInfo.getTitle());
                });
    }

    @Test
    void addMovieInfo(){
        var movieInfo = new MovieInfo("mockID", "Batman Begins1",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

        when(movieInfoServiceMock.addMovieInfo(isA(MovieInfo.class))).thenReturn(Mono.just(movieInfo));

        webTestClient.post()
                .uri(MOVIE_INFO_URL)
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(MovieInfo.class)
                .consumeWith(InfoEntityExchangeResult -> {

                    var saveMovieInfo = InfoEntityExchangeResult.getResponseBody();
                    assert saveMovieInfo != null;
                    assert saveMovieInfo.getMovieInfoID() != null;
                    assertEquals("mockID", saveMovieInfo.getMovieInfoID());

                });
    }

    @Test
    void updateMovieInfo(){
        var id = "abc";
        var updateMovieInfoRecord = new MovieInfo(id, "Batman Begins3",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));



        when(movieInfoServiceMock.updateMovieInfo(isA(MovieInfo.class), isA(String.class))).thenReturn(Mono.just(updateMovieInfoRecord));

        webTestClient.put()
                .uri(MOVIE_INFO_URL+"/{id}","abc")
                .bodyValue(updateMovieInfoRecord)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(MovieInfo.class)
                .consumeWith(InfoEntityExchangeResult -> {

                    var updateMovieInfo = InfoEntityExchangeResult.getResponseBody();
                    assert updateMovieInfo != null;
                    assert updateMovieInfo.getMovieInfoID() != null;
                    assertEquals("Batman Begins3", updateMovieInfo.getTitle());

                });
    }

    @Test
    void addMovieInfo_validation(){
        var movieInfo = new MovieInfo(null, "Batman Begins1",
                -2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

//        when(movieInfoServiceMock.addMovieInfo(isA(MovieInfo.class))).thenReturn(Mono.just(movieInfo));

        webTestClient.post()
                .uri(MOVIE_INFO_URL)
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(String.class)
                .consumeWith(InfoEntityExchangeResult -> {
                    var saveMovieInfo = InfoEntityExchangeResult.getResponseBody();
                    assert saveMovieInfo != null;
                    System.out.println(saveMovieInfo);
                });
//                .expectBody(MovieInfo.class)
//                .consumeWith(InfoEntityExchangeResult -> {
//
//                    var saveMovieInfo = InfoEntityExchangeResult.getResponseBody();
//                    assert saveMovieInfo != null;
//                    assert saveMovieInfo.getMovieInfoID() != null;
//                    assertEquals("mockID", saveMovieInfo.getMovieInfoID());
//
//                });
    }
}
