package com.reactivespring.moviesinfoservice.service;

import com.reactivespring.moviesinfoservice.domain.MovieInfo;
import com.reactivespring.moviesinfoservice.repository.MovieInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MovieInfoService {
    private MovieInfoRepository movieInfoRepository;
    public MovieInfoService(MovieInfoRepository movieInfoRepository) {
        this.movieInfoRepository = movieInfoRepository;
    }

    public Mono<MovieInfo> addMovieInfo(MovieInfo movieInfo) {
        return movieInfoRepository.save(movieInfo).log();
    }

    public Flux<MovieInfo> getAllMovies() {
        return movieInfoRepository.findAll().log();
    }



    public Mono<MovieInfo> getMovieByID(String id) {
        return movieInfoRepository.findById(id).log();
    }

    public Mono<MovieInfo> updateMovieInfo(MovieInfo updateMovieInfo, String id) {
        return movieInfoRepository.findById(id)
                .flatMap(movieInfo ->
                        {
                            movieInfo.setCast(updateMovieInfo.getCast());
                            movieInfo.setTitle(updateMovieInfo.getTitle());
                            movieInfo.setYear(updateMovieInfo.getYear());
                            movieInfo.setReleaseDate(updateMovieInfo.getReleaseDate());
                            return movieInfoRepository.save(movieInfo).log();
                        }

                        );
    }

    public Flux<MovieInfo> getMovieByYear(Integer year) {
        return movieInfoRepository.findByYear(year).log();
    }

    public Flux<MovieInfo> getMovieInfoByName(String name) {
        return movieInfoRepository.findByTitle(name).log();
    }
//    public Mono<Void> deleteMovieInfo(String id) {
//        return movieInfoRepository.deleteById(id).log();
//    }
//    public Mono<MovieInfo> getMovieByID(MovieInfo movieId) {
//        return movieInfoRepository.findById(movieId.getMovieInfoID());
//    }
}
