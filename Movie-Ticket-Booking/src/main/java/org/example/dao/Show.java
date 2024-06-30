package org.example.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Show {

    private final String id;

    private final Screen screen;

    private final Movie movie;

    private final Date startTime;

    private final Integer durationInSecs;
}
