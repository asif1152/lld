package org.example.models.consumer;

import org.example.models.Broker.Broker;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@lombok.Getter
public class Consumer extends Thread {

    String uuid;
    List<String> topicNames;

    Broker broker;

    Boolean isAlive;
    Lock isAliveLock;

    Integer limit;

    public Consumer(Broker broker, Integer limit){
        this.uuid = UUID.randomUUID().toString();
        this.broker = broker;
        this.isAlive = true;
        this.isAliveLock = new ReentrantLock();
        this.limit = limit;
    }

    public void subscribeTopic(String topicName){
        this.broker.subscribeTopic(topicName, this);
    }

    public void startConsuming(){
        this.start();
    }

    public void stopConsumer(){
        this.isAliveLock.lock();
        this.isAlive = false;
        this.isAliveLock.unlock();
    }

    public void run(){
        this.poll();
    }

    private void poll() {

        while(true){
            this.isAliveLock.lock();
            if(!this.isAlive){
                System.out.printf("Consumer with uuid: %s is dead, stopping it\n", this.getUuid());
                this.isAliveLock.unlock();
                break;
            }
            this.isAliveLock.unlock();

            List<String> consumerData = this.broker.getConsumerData(this.uuid);
            if(Objects.isNull(consumerData) || consumerData.size()==0){
                System.out.printf("No data available right now for consumer : %s\n", this.uuid);
            }
            for(String data: consumerData){
                System.out.printf("Data received for consumer with uuid: %s, is: %s\n", this.uuid, data);
            }
            try{
                Thread.sleep(1000);
            } catch (InterruptedException ex){
                System.out.printf("got interrupted during sleep of consumer with uuid: %s\n", this.getUuid());
            }
        }

    }

}
