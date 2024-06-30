package org.example.services;

import org.example.dao.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MovieService {

    private Map<String, Movie> movieMap;

    public MovieService(){
        this.movieMap = new HashMap<>();
    }

    public Movie createMovie(String name){
        Movie movie = new Movie(UUID.randomUUID().toString(), name);
        movieMap.put(movie.getId(), movie);
        return movie;
    }

    public Movie getMovie(String id){
        return movieMap.get(id);
    }

}
