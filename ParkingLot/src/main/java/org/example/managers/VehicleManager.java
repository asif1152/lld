package org.example.managers;

import org.example.models.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleManager {

    private Map<String, List<Vehicle>> colorVsVehicleMap;
    private Map<String, Vehicle> regVsVehicleMap;

    private List<Vehicle> vehicleList;

    public VehicleManager(){
        this.colorVsVehicleMap = new HashMap<>();
        this.regVsVehicleMap = new HashMap<>();
        this.vehicleList = new ArrayList<>();
    }

    public void addVehicle(Vehicle v){
        this.vehicleList.add(v);
        if(!this.colorVsVehicleMap.containsKey(v.getColor())){
            this.colorVsVehicleMap.put(v.getColor(), new ArrayList<>());
        }
        this.colorVsVehicleMap.get(v.getColor()).add(v);
        this.regVsVehicleMap.put(v.getRegNo(),v);
    }

    public void removeVehicle(Vehicle v){
        this.vehicleList.remove(v);
        this.colorVsVehicleMap.get(v.getColor()).remove(v);
        this.regVsVehicleMap.remove(v.getRegNo());
    }

    public List<Vehicle> getColorVehicles(String color){
        return this.colorVsVehicleMap.get(color);
    }

    public Vehicle getRegVehicle(String regNo){
        return this.regVsVehicleMap.get(regNo);
    }

    public List<Vehicle> getAllVehicles(){
        return this.vehicleList;
    }

}
