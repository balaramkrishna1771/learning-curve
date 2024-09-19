package com.reactivespring.moviesinfoservice.controller;

import com.reactivespring.moviesinfoservice.domain.MovieInfo;
import com.reactivespring.moviesinfoservice.service.MovieInfoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1")
@Slf4j
public class MovieInfoController {


    private MovieInfoService movieInfoService;

    public MovieInfoController(MovieInfoService movieInfoService) {
        this.movieInfoService = movieInfoService;
    }

    @GetMapping("/movieinfos")
    public Flux<MovieInfo> getAllMovies(@RequestParam(value = "year", required = false) Integer year){
        log.info("Year is : {}", year);
        if(year != null){
            return movieInfoService.getMovieByYear(year);
        }
        return movieInfoService.getAllMovies();
    }

    @GetMapping("/movieinfos/name")
    public Flux<MovieInfo> getMovieInfoByName(@RequestParam(value = "name", required = false) String name){
        log.info("Name is : {}", name);
        if(name != null){
            return movieInfoService.getMovieInfoByName(name);
        }
        return movieInfoService.getAllMovies();
    }

    @GetMapping("/movieinfos/{id}")
    public Mono<ResponseEntity<MovieInfo>> getMovieByID(@PathVariable String id){
        return movieInfoService.getMovieByID(id)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .log();
    }

    @PostMapping("/movieinfos")
    @ResponseStatus(CREATED)
    public Mono<MovieInfo> addMovieInfo(@RequestBody @Valid MovieInfo movieInfo){
        return movieInfoService.addMovieInfo(movieInfo);
    }

    @PutMapping("/movieinfos/{id}")
    public Mono<ResponseEntity<MovieInfo>> updateMovieInfo(@RequestBody MovieInfo movieInfo, @PathVariable String id){
        return movieInfoService.updateMovieInfo(movieInfo, id)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .log();
    }

//    @GetMapping("/movieinfos/{id}")
//    public Flux<MovieInfo> getMovieByYear(@RequestParam(value = "year", required = false) Integer year){
//        if(year != null){
//            return movieInfoService.getMovieByYear(year).log();
//        }
//
//        return movieInfoService.getAllMovies();
//    }


//    @PutMapping("/movieinfos/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public Mono<Void> deleteMovieInfo(@PathVariable String id){
//        return movieInfoService.deleteMovieInfo(id);
//    }

//    @GetMapping("/movie/{movieId}")
//    public Mono<MovieInfo> getMovieInfo(@PathVariable MovieInfo movieId){
//        return movieInfoService.getMovieByID(movieId);
//    }

}
