package org.example.dao;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Theatre {

    private final String id;
    private final String name;

    private List<Screen> screenList;

    public Theatre(String id, String name){
        this.id = id;
        this.name = name;
        this.screenList = new ArrayList<>();
    }

    public void addScreen(Screen screen){
        this.screenList.add(screen);
    }


}
