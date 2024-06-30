package org.example;

import org.example.inputreader.CustomFileReader;
import org.example.inputreader.InteractiveReader;
import org.example.managers.ParkingLotService;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        ParkingLotService parkingLotService = new ParkingLotService();
        if (isFileMode(args)){
            new CustomFileReader(parkingLotService, args[0]).readData();
        }else if (isInteractiveMode(args)){
            new InteractiveReader(parkingLotService).readData();
        }
    }

    private static boolean isFileMode(String[] args){
        return args.length > 0;
    }

    private static boolean isInteractiveMode(String[] args){
        return args.length == 0;
    }

}