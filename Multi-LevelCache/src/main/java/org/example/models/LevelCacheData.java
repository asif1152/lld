package org.example.models;

@lombok.AllArgsConstructor
@lombok.Getter
public class LevelCacheData {
    int readTime;
    int writeTime;
    int capacity;
}
