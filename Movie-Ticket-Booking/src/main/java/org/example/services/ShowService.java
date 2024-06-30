package org.example.services;

import org.example.dao.Movie;
import org.example.dao.Screen;
import org.example.dao.Show;
import org.example.exceptions.NotFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowService {

    private Map<String, Show> showMap;


    public ShowService(){
        showMap = new HashMap<>();
    }

    public Show createShow(Movie movie, Screen screen, Date startTime, Integer durationInSecs){
        Show show = new Show(UUID.randomUUID().toString(), screen, movie,startTime, durationInSecs);
        showMap.put(show.getId(), show);
        return show;
    }

    public Show getShow(String id){
        if(!showMap.containsKey(id)){
            throw new NotFoundException();
        }
        return showMap.get(id);
    }



}
