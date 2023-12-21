package service;

import adapter.ParkingRepository;
import model.Constants;
import model.ParkingTicket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

public class ParkingService {

    private final int parkingCostPerHour = Constants.parkingCostPerHour;
    static final ZoneId zone = Constants.asiaZone;
    private final ParkingRepository parkingRepository;
    public ParkingService(){
        this.parkingRepository = new ParkingRepository();
    }

    public String alotParking(ParkingTicket parkingTicket, Integer parkDurationInHr){
        if(parkingTicket != null && parkingTicket.getCarName() != null && parkDurationInHr > 0){
            if(parkingRepository.getRemainingSlot() > 0){
                parkingTicket.setEntryTime(LocalDateTime.now(zone));
                parkingTicket.setExitTime(parkingTicket.getEntryTime().plusHours(parkDurationInHr));
                parkingTicket.setTicketId(UUID.randomUUID().toString());
                parkingRepository.assignParking(parkingTicket);
                return parkingTicket.getTicketId();
            }
            throw new RuntimeException("Parking is Full");
        }
        throw new NullPointerException("Invalid Entry");
    }

    public void checkOut(String ticketId){
        if(ticketId != null){
            ParkingTicket parkingTicket = parkingRepository.getTicketDetails(ticketId);
            System.out.println("Ticket Id -> " + ticketId);
            System.out.println("Car Name -> " + parkingTicket.getCarName());
            long totalCost = calculateCost(parkingTicket.getEntryTime(),parkingTicket.getExitTime());
            Boolean customerPayedBill = isCustomerPayBill(totalCost);
            if(customerPayedBill){
                parkingRepository.disassociateParking(ticketId);
            }else{
                System.out.println("Please Pay Bill Amount");
            }
        }else{
            throw new RuntimeException("TicketId is null");
        }
    }

    private Boolean isCustomerPayBill(long totalCost) {
        System.out.println("Total Bill Amount : " + Math.round(totalCost));
        return true;
    }

    public long calculateCost(LocalDateTime entryTime, LocalDateTime exitTime){
        if(entryTime.isBefore(exitTime)){
            Duration duration = Duration.between(entryTime,exitTime);
            long hours = duration.toHours();
            return hours * this.parkingCostPerHour;
        }
        throw new RuntimeException("Entry Time / Exit Time is not valid");
    }

    public int getRemainingSlots(){
        return parkingRepository.getRemainingSlot();
    }

    public List<ParkingTicket> getAllParkingDetails() {
        return parkingRepository.getAllList();
    }
}
