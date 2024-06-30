package org.example.inputreader;

import org.example.managers.ParkingLotService;
import org.w3c.dom.xpath.XPathResult;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class IReader {
    private ParkingLotService parkingLotService;
    public IReader(ParkingLotService parkingLotService){
        this.parkingLotService = parkingLotService;
    }

    public abstract void readData() throws IOException;

    protected void process(String input){
        // TODO: for each command, we can use a command pattern
        // TODO: we can have command factory to give command
        // TODO: Each command will have parkingService as an object through which it can execute
        String[] params = input.strip().split(" ");
        String command = params[0].strip();
        if(command.equals("create_parking_lot")){

            int noSlots = Integer.parseInt(params[1].strip());
            this.parkingLotService.createSlots(noSlots);
        }
        else if(command.equals("park")){
            String regNo = params[1].strip();
            String carColor = params[2].strip();
            this.parkingLotService.park(carColor, regNo);
        }
        else if(command.equals("leave")){
            int slotNo = Integer.parseInt(params[1]);
            this.parkingLotService.leave(slotNo);
        }
        else if(command.equals("status")){
            this.parkingLotService.getCurrentStatus();
        }
        else if(command.equals("registration_numbers_for_cars_with_colour")){
            String color = params[1].strip();
            this.parkingLotService.getRegNoWithColor(color);
        }
        else if(command.equals("slot_numbers_for_cars_with_colour")){
            String color = params[1];
            this.parkingLotService.getSlotNoWithColor(color);
        }
        else if(command.equals("slot_number_for_registration_number")){
            String regNo = params[1].strip();
            this.parkingLotService.getSlotNoForReg(regNo);
        }else{
            System.out.printf("got an invalid command: %s, exiting", command);
            System.exit(-1);
        }

    }
}
