package org.example.models.Broker;

import org.example.exceptions.InValidPartitionException;
import org.example.exceptions.PartitionNotFoundException;
import org.example.exceptions.TopicNotFoundException;
import org.example.models.DataPartitionStrategy;
import org.example.models.Topic;
import org.example.models.TopicPartition;
import org.example.models.consumer.Consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Broker {

    Map<String, Topic> topicMap;
    Map<String, Consumer> consumerMap;
    Map<Consumer, Map<TopicPartition, Integer>> consumerTopicOffsetMap;

    //Broker SingletonInstance;
    public Broker(){

        this.topicMap = new HashMap<>();
        this.consumerMap = new HashMap<>();
        this.consumerTopicOffsetMap = new HashMap<>();
    }

    public void createTopic(String tpNm, int noPart, DataPartitionStrategy dataPartitionStrategy){
        Topic tp = new Topic(tpNm, noPart, dataPartitionStrategy);
        this.topicMap.put(tpNm, tp);
    }
    public void addPart(String tpNm) throws TopicNotFoundException {
        if(!this.topicMap.containsKey(tpNm)){
            throw new TopicNotFoundException();
        }
        this.topicMap.get(tpNm).addPartition();
    }

    public void publishData(String tpNm, String data) throws TopicNotFoundException {
        if(!this.topicMap.containsKey(tpNm)){
            throw new TopicNotFoundException();
        }
        this.topicMap.get(tpNm).publishData(data);
    }

    public void subscribeTopic(String tpName, Consumer consumer){
        this.consumerMap.put(consumer.getUuid(), consumer);
        Integer noPart = this.topicMap.get(tpName).getNoOfPartitions();
        if(!this.consumerTopicOffsetMap.containsKey(consumer)){
            Map<TopicPartition, Integer> topicPartitionIntegerMap = new HashMap<>();
            this.consumerTopicOffsetMap.put(consumer, topicPartitionIntegerMap);
        }
        Map<TopicPartition,Integer> topicPartitionIntegerMap = this.consumerTopicOffsetMap.get(consumer);
        for(int i=0;i<noPart;++i){
            TopicPartition topicPartition = new TopicPartition(tpName, i);
            topicPartitionIntegerMap.put(topicPartition, -1);
        }
        this.consumerTopicOffsetMap.put(consumer, topicPartitionIntegerMap);
    }

    public List<String> getConsumerData(String uuid){
        Consumer consumer = this.consumerMap.get(uuid);
        List<String> data = new ArrayList<>();
        for(TopicPartition topicPartition: this.consumerTopicOffsetMap.get(consumer).keySet()){
            Integer offset = this.consumerTopicOffsetMap.get(consumer).get(topicPartition)+1;
            try{
                data.addAll(this.topicMap.get(topicPartition.getTopic()).getData(topicPartition.getPart(), offset,
                        consumer.getLimit()));
            }
            catch (Exception ex){
                System.out.printf("error getting data from partition for topic: %s. partition: %d",
                        topicPartition.getTopic(), topicPartition.getPart());
            }
            this.consumerTopicOffsetMap.get(consumer).put(topicPartition, offset+consumer.getLimit()-1);
        }
        return data;
    }




}
