package adapter;

import model.Constants;
import model.ParkingTicket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingRepository {
    private final Integer maximumSlot = Constants.parkingMaximumSlot;
    private Integer reservedSlots = 0;
    private final Map<String, ParkingTicket> parkingList = new HashMap<>();

    public int getRemainingSlot(){
        return this.maximumSlot - this.reservedSlots;
    }

    public void assignParking(ParkingTicket parkingTicket) {
        this.reservedSlots++;
        this.parkingList.put(parkingTicket.getTicketId(), parkingTicket);
    }

    public void disassociateParking(String ticketId){
        if(this.parkingList.containsKey(ticketId)){
            this.parkingList.remove(ticketId);
            this.reservedSlots--;
        }
    }

    public ParkingTicket getTicketDetails(String ticketId) {
        if(parkingList.containsKey(ticketId)){
            return parkingList.get(ticketId);
        }
        throw new RuntimeException("Ticket Invalid");
    }

    public List<ParkingTicket> getAllList() {
        return new ArrayList<>(this.parkingList.values());
    }
}
