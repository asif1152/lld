package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LevelReadResponse<V> {
    int readTime;
    V val;
}
