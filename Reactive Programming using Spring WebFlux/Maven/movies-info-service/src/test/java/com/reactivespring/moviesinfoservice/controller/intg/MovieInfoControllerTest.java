package com.reactivespring.moviesinfoservice.controller.intg;

import com.reactivespring.moviesinfoservice.domain.MovieInfo;
import com.reactivespring.moviesinfoservice.repository.MovieInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class MovieInfoControllerTest {

    @Autowired
    WebTestClient webClient;

    @Autowired
    MovieInfoRepository movieInfoRepository;


    static  String MOVIE_INFO_URL = "/v1/movieinfos";
    @BeforeEach
    void setUp(){
        var movieinfos = List.of(new MovieInfo(null, "Batman Begins",
                        2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")),
                new MovieInfo(null, "The Dark Knight",
                        2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18")),
                new MovieInfo("abc", "Dark Knight Rises",
                        2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-20")));

        movieInfoRepository.saveAll(movieinfos)
                .blockLast();
    }

    @AfterEach
    void tearDown(){
        movieInfoRepository.deleteAll().block();
    }

    @Test
    void addMovieInfo(){
        var movieInfo = new MovieInfo(null, "Batman Begins1",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));
        webClient.post()
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

                });
    }

    @Test
    void getMovieInfos(){
         webClient.get()
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

    @Test
    void getMovieByID() {

        webClient.get()
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
    void updateMovieInfo(){
        var updateMovieInfoRecord = new MovieInfo(null, "Batman Begins3",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));
        webClient.put()
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
    void updateMovieInfo_notfFound(){
        var updateMovieInfoRecord = new MovieInfo(null, "Batman Begins3",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));
        webClient.put()
                .uri(MOVIE_INFO_URL+"/{id}","def")
                .bodyValue(updateMovieInfoRecord)
                .exchange()
                .expectStatus()
                .isNoContent();
    }
    @Test
    void getMovieByID_notFound() {

        webClient.get()
                .uri(MOVIE_INFO_URL + "/def")
                .exchange()
                .expectStatus()
                .isNotFound();
    }


    @Test
    void getMovieByYear(){

//        var movieInfoFlux = movieInfoRepository.findByYear(2005);

        var uri = UriComponentsBuilder.fromUriString(MOVIE_INFO_URL)
                        .queryParam("year","2005")
                                .buildAndExpand().toUri();
        webClient.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(MovieInfo.class)
                .hasSize(1);

//        StepVerifier.create(movieInfoFlux)
//                .expectNextCount(1)
//                .verifyComplete();
    }


    @Test
    void getMovieByName(){

//        var movieInfoFlux = movieInfoRepository.findByYear(2005);

        var uri = UriComponentsBuilder.fromUriString(MOVIE_INFO_URL+"/name")
                .queryParam("name","Dark Knight Rises")
                .buildAndExpand().toUri();
        webClient.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(MovieInfo.class)
                .hasSize(1);

//        StepVerifier.create(movieInfoFlux)
//                .expectNextCount(1)
//                .verifyComplete();
    }
//    @Test
//    void deleteMovieInfo() {
//        var movieId = "abc";
//
//        webClient.delete()
//                .uri(MOVIE_INFO_URL + "/{id}", movieId)
//                .exchange()
//                .expectStatus()
//                .isNoContent();
//    }
}