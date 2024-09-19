package com.reactivespring.moviesinfoservice.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class MovieInfo {

    @Id
    private String movieInfoID;
    @NotBlank(message = "movieInfo.name must be present")
    private String title;
    @NotNull(message = "movieInfo.year must not be null")
    @Positive(message = "movieInfo.year must me positive")
    private Integer year;

    private List<@NotBlank(message = "movie cast should be not null ") String> cast;
    private LocalDate releaseDate;

}
