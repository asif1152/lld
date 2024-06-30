package org.example;

import org.example.dao.*;
import org.example.exceptions.SeatTemporaryUnavailableException;
import org.example.services.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class Driver {

    private static void printAvailableSeats(List<Seat> seatList){

        for(Seat seat: seatList){
            System.out.printf("Seat id: %s, seat row: %d, seatNo: %d\n", seat.getId(), seat.getRowNo(),
                    seat.getSeatNo());

        }
    }

    public static void main(String[] args) throws InterruptedException {

        TheatreService theatreService = new TheatreService();

        Theatre theatre = theatreService.createTheatre("BLR-Theatre");

        Screen screen = theatreService.createScreen(theatre);

        Seat seat1 = theatreService.addSeatInScreen(1, 1, screen);
        Seat seat2 = theatreService.addSeatInScreen(1, 2, screen);
        Seat seat3 = theatreService.addSeatInScreen(1, 3, screen);

        MovieService movieService = new MovieService();
        Movie movie = movieService.createMovie("Test_Movie");

        ShowService showService = new ShowService();

        Show show = showService.createShow(movie, screen, new Date(2023, Calendar.JULY, 20, 15, 30),
                2*60*60);

        SeatLocker seatLocker = new InMemorySeatLocker(60);

        BookingService bookingService = new BookingService(seatLocker);

        SeatAvailabilityService seatAvailabilityService = new SeatAvailabilityService(bookingService, seatLocker);

        class Thread1 extends Thread{

            public void run(){

                List<Seat> availableSeatUser1 = seatAvailabilityService.getAvailableSeats(show);

                System.out.println("Available Seats for user: asif");

                printAvailableSeats(availableSeatUser1);

                List<Seat> selectedSeatUser1 = new ArrayList<>();
                selectedSeatUser1.add(availableSeatUser1.get(0));
                selectedSeatUser1.add(availableSeatUser1.get(1));
                try{
                    bookingService.createBooking("asif", show, selectedSeatUser1);
                }
                catch (SeatTemporaryUnavailableException ex){
                    System.out.printf("could not book seats for user asif due to: %s", ex.getMessage());
                }



            }
        }

        class Thread2 extends Thread{

            public void run(){

                List<Seat> availableSeatUser2 = seatAvailabilityService.getAvailableSeats(show);

                System.out.println("Available Seats for user: salman");
                printAvailableSeats(availableSeatUser2);

                List<Seat> selectedSeatUser2 = new ArrayList<>();
                selectedSeatUser2.add(availableSeatUser2.get(2));
                //selectedSeatUser2.add(availableSeatUser2.get(1));

                try{
                    bookingService.createBooking("salman", show, selectedSeatUser2);
                }
                catch (SeatTemporaryUnavailableException ex){
                    System.out.printf("could not book seats for user salman due to: %s", ex.getMessage());
                }

            }
        }

        Thread1 thread1 = new Thread1();

        thread1.start();

        Thread2 thread2 = new Thread2();
        thread2.start();

        thread1.join();
        thread2.join();























    }
}