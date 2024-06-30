package org.example.inputreader;

import org.example.managers.ParkingLotService;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class InteractiveReader extends IReader{
    public InteractiveReader(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    @Override
    public void readData() throws IOException {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(true){
            if(Objects.equals(input.strip(), "exit"))
                break;
            this.process(input);
            input = scanner.nextLine();
        }

    }
}
