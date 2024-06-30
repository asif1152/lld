package org.example;

import org.example.models.Broker.Broker;
import org.example.models.RoundRobinDataPartStrategy;
import org.example.models.consumer.Consumer;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        RoundRobinDataPartStrategy roundRobinDataPartStrategy = new RoundRobinDataPartStrategy();
        Broker broker  = new Broker();
        String tpNm = "test1";
        String tpNm2 = "test2";
        // create topic
        broker.createTopic(tpNm, 2, roundRobinDataPartStrategy);
        broker.createTopic(tpNm2, 2, roundRobinDataPartStrategy);

        // produce data
        try {
            broker.publishData(tpNm, "My name is asif-1");
            broker.publishData(tpNm, "My name is asif-2");
            broker.publishData(tpNm, "My name is asif-3");
            broker.publishData(tpNm, "My name is asif-4");
            broker.publishData(tpNm2, "My name is khan-1");
            broker.publishData(tpNm2, "My name is khan-2");
            broker.publishData(tpNm2, "My name is khan-3");

        }catch (Exception ex){
            System.out.printf("could not publish data due to: %s\n", ex.toString());
            return;
        }
        // consume data
        Consumer consumer = new Consumer(broker, 2);
        consumer.subscribeTopic(tpNm);
        consumer.subscribeTopic(tpNm2);
        consumer.startConsuming();
        consumer.join();
    }
}