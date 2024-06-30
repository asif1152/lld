package org.example.models;

import javax.xml.crypto.Data;

public class RoundRobinDataPartStrategy implements DataPartitionStrategy {
    Integer lastPart;
    public RoundRobinDataPartStrategy(Integer lastPart){
        this.lastPart = lastPart;
    }
    public RoundRobinDataPartStrategy(){
        this.lastPart = -1;
    }
    @Override
    public Integer getNextPartition(Integer totalPartitions) {
        return (this.lastPart + 1)%totalPartitions;
    }
}
