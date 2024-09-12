package com.reactivespring.moviesinfoservice.controller;

import com.reactivespring.moviesinfoservice.domain.MovieInfo;
import com.reactivespring.moviesinfoservice.service.MovieInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.List;

import static reactor.core.publisher.Mono.when;

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


        when(movieInfoServiceMock.getAllMovies()).thenReturn(movieinfos);
        
        webTestClient.get()
                .uri(MOVIE_INFO_URL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(MovieInfo.class)
                .hasSize(3);

//        StepVerifier.create(movieInfos_1)
//                .expectNextCount(3)
//                .verifyComplete();
    }
}
