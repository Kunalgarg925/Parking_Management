package DaoLevel;

import Model.ParkingTicket;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingDatabase {
    private final Integer parkingMaximumSlot = 100;
    private Integer parkingUsedSlot = 0;
    private final Map<String, ParkingTicket> parkingList = new HashMap<>();

    public int getRemainingSlot(){
        return this.parkingMaximumSlot - this.parkingUsedSlot;
    }

    public void assignParking(ParkingTicket newCustomer) {
        this.parkingUsedSlot++;
        this.parkingList.put(newCustomer.getTicketId(), newCustomer);
    }

    public void disassociateParking(String ticketId){
        if(this.parkingList.containsKey(ticketId)){
            this.parkingList.remove(ticketId);
            this.parkingUsedSlot--;
        }
    }

    public ParkingTicket getTicketDetails(String ticketId) {
        if(parkingList.containsKey(ticketId)){
            return parkingList.get(ticketId);
        }
        throw new RuntimeException("Ticket Invalid");
    }

    public List<ParkingTicket> getAllList() {
        return new ArrayList<ParkingTicket> (this.parkingList.values());
    }
}
