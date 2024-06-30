package org.example.models;

public interface DataPartitionStrategy {

    Integer getNextPartition(Integer totalPartitions);
}
