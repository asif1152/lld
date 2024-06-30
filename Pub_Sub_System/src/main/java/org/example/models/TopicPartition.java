package org.example.models;

public class TopicPartition {

    String topicName;

    Integer part;

    public TopicPartition(String tp, Integer part){
        this.topicName = tp;
        this.part = part;
    }

    public String getTopic(){
        return this.topicName;
    }

    public Integer getPart(){
        return this.part;
    }

}
