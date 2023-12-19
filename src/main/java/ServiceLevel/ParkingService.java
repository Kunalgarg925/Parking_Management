package ServiceLevel;

import DaoLevel.ParkingRepository;
import Model.ConstantData;
import Model.ParkingTicket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ParkingService {

    private final int parkingCostPerHour = ConstantData.parkingCostPerHour;
    static final ZoneId zone = ConstantData.zone;
    private final ParkingRepository parkingRepository;
    public ParkingService(){
        this.parkingRepository = new ParkingRepository();
    }

    public String alotParking(ParkingTicket newCustomer, Integer parkDuration){
        if(newCustomer != null && parkDuration > 0 && newCustomer.getCarName() != null){
            if(parkingRepository.getRemainingSlot() > 0){
                newCustomer.setEntryTime(LocalDateTime.now(zone));
                newCustomer.setExitTime(newCustomer.getEntryTime().plusHours(parkDuration));
                newCustomer.setTicketId(UUID.randomUUID().toString());
                parkingRepository.assignParking(newCustomer);
                return newCustomer.getTicketId();
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
            throw new NullPointerException("TicketId is null");
        }
    }

    private Boolean isCustomerPayBill(long totalCost) {
        System.out.println("Total Bill Amount : " + Math.round(totalCost));
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Enter Amount : ");
//        int paymentReceived = scan.nextInt();
//        if(Math.round(totalCost) == paymentReceived){
//            return true;
//        }
//        return false;
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
