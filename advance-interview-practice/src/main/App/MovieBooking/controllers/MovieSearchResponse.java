package MovieBooking.controllers;

import MovieBooking.entity.Show;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public class MovieSearchResponse {
    @JsonProperty("movie_shows")
    List<Show> shows;
    int page;
    int limit;
}
