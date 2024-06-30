package org.example.models;

import org.example.exceptions.InValidPartitionException;
import org.example.exceptions.PartitionNotFoundException;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Topic {

    String name;
    Integer noOfPartitions;

    DataPartitionStrategy dataPartitionStrategy;

    List<ArrayList<String>> partList;

    Lock noOfPartLock;
    public Topic(String nm, Integer noPart, DataPartitionStrategy dataPartitionStrategy){
        this.name = nm;
        this.noOfPartitions= noPart;
        this.dataPartitionStrategy= dataPartitionStrategy;
        this.noOfPartLock = new ReentrantLock();
        this.partList = new ArrayList<>();
        for(int i=0;i<noPart;++i){
            this.partList.add(new ArrayList<>());
        }
    }

    public List<String> getData(Integer part, Integer offset, Integer limit) throws PartitionNotFoundException, InValidPartitionException {
        if(part<0){
            throw new InValidPartitionException();
        }
        this.noOfPartLock.lock();
        if(part >=  this.partList.size()){
            throw new PartitionNotFoundException();
        }
        this.noOfPartLock.unlock();

        List<String> partDataList = new ArrayList<>();
        for(int i=offset; i < offset+limit && i<this.partList.get(part).size();++i){
            partDataList.add(this.partList.get(part).get(i));
        }

        return partDataList;
    }
    public void publishData(String data){
        this.noOfPartLock.lock();
        int nextPart = this.dataPartitionStrategy.getNextPartition(this.noOfPartitions);
        this.noOfPartLock.unlock();
        this.partList.get(nextPart).add(data);
        System.out.println("data published successfully");
    }
    public Integer getNoOfPartitions(){
        Integer noOfPart;
        this.noOfPartLock.lock();
        noOfPart = this.noOfPartitions;
        this.noOfPartLock.unlock();
        return noOfPart;
    }
    public void addPartition(){
        this.noOfPartLock.lock();
        this.partList.add(new ArrayList<>());
        this.noOfPartitions +=1;
        this.noOfPartLock.unlock();
    }
}
