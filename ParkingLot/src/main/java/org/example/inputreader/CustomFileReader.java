package org.example.inputreader;

import org.example.managers.ParkingLotService;

import java.io.*;

public class CustomFileReader extends IReader{

    private final String filePath ;
    private String fileName;
    public CustomFileReader(ParkingLotService parkingLotService, String fileName) {
        super(parkingLotService);
        this.fileName = fileName;
        this.filePath = "C:\\Users\\Mohammad Asif\\Documents\\lld\\ParkingLot\\src\\main\\java\\org\\example\\";
    }

    @Override
    public void readData() throws IOException {

        File file = new File(this.filePath +this.fileName);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

       String input = bufferedReader.readLine();
       while(input != null){
           this.process(input);
           input = bufferedReader.readLine();
       }
    }
}
