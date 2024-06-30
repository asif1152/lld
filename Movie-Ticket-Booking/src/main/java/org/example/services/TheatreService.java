package org.example.services;

import org.example.dao.Screen;
import org.example.dao.Seat;
import org.example.dao.Theatre;
import org.example.exceptions.NotFoundException;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TheatreService {

    private Map<String, Theatre> theatreMap;
    private Map<String, Screen> screenMap;

    private Map<String, Seat> seatMap;


    public TheatreService(){
        theatreMap = new HashMap<>();
        screenMap = new HashMap<>();
        seatMap = new HashMap<>();
    }

    public Theatre createTheatre(String name){
        String id = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(id, name);

        theatreMap.put(id, theatre);
        return theatre;
    }

    public Screen createScreen(Theatre theatre){
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, theatre);
        screenMap.put(screenId, screen);
        theatre.addScreen(screen);
        return screen;
    }

    public Seat addSeatInScreen(Integer rowNo, Integer seatNo, Screen screen){
        Seat seat = createSeat(rowNo,seatNo);
        screen.addSeat(seat);
        return seat;
    }

    private Seat createSeat(Integer rowNo, Integer seatNo){
        Seat seat = new Seat(UUID.randomUUID().toString(), rowNo, seatNo);
        seatMap.put(seat.getId(), seat);
        return seat;
    }

    public Theatre getTheatre(String id){
        if(!theatreMap.containsKey(id)){
            throw new NotFoundException();
        }
        return theatreMap.get(id);
    }

    public Screen getScreen(String id){
        if (!screenMap.containsKey(id)){
            throw new NotFoundException();
        }
        return screenMap.get(id);

    }
    public Seat getSeat(String id){
        if (!seatMap.containsKey(id)){
            throw new NotFoundException();
        }
        return seatMap.get(id);
    }
}
