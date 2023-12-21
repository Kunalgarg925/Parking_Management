import Model.CarType;
import Model.ParkingTicket;
import ServiceLevel.ParkingService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
public class ParkingManagementTest {
    ParkingService parkingService = new ParkingService();
    public List<String> alotSomeParkings(){
        List<String> ticketIdList = new ArrayList<>();
        ParkingTicket pt1 = createTicket("Nexon", CarType.ElectricCars);
        ParkingTicket pt2 = createTicket("XUV300", CarType.Suv);
        ParkingTicket pt3 = createTicket("Audi", CarType.SportsCars);
        ticketIdList.add(parkingService.alotParking(pt1,2));
        ticketIdList.add(parkingService.alotParking(pt2,3));
        ticketIdList.add(parkingService.alotParking(pt3,2));
        return ticketIdList;
    }
    @Test
    void testParkingAlotment(){
        alotSomeParkings();
        ParkingTicket pt1 = createTicket("BMW", CarType.LuxuryCars);
        ParkingTicket pt2 = createTicket(null, CarType.Suv);
        ParkingTicket pt3 = createTicket("Fortuner", CarType.HybridCars);
        ParkingTicket pt4 = createTicket("Harrier",CarType.HybridCars);
        ParkingTicket pt5 = createTicket("Thar", CarType.HybridCars);
        assertNotNull(parkingService.alotParking(pt1,2));
        assertThrows(NullPointerException.class, () -> {parkingService.alotParking(pt2,3);});
        assertThrows(NullPointerException.class, () -> {parkingService.alotParking(pt3,0);});
        assertNotNull(parkingService.alotParking(pt4,3));
        System.out.println(parkingService.getAllParkingDetails().size());
        assertThrows(RuntimeException.class, () -> {parkingService.alotParking(pt5,1);});
    }

    @Test
    void getAllParkingDetails(){
        alotSomeParkings();
        System.out.println(parkingService.getAllParkingDetails().size());
        assertNotNull(parkingService.getAllParkingDetails());
    }

    @Test
    void testRemainingParkingSlot(){
        alotSomeParkings();
        assertEquals(2,parkingService.getRemainingSlots());
    }

    @Test
    void checkOutCustomerVechile(){
        List<String> ticketIdList = alotSomeParkings();
        assertDoesNotThrow(() ->{parkingService.checkOut(ticketIdList.get(0));});
        assertThrows(NullPointerException.class, () -> {parkingService.checkOut(null);});
    }

    private static ParkingTicket createTicket(String carName, CarType carType) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setCarName(carName);
        parkingTicket.setCarType(carType);
        return parkingTicket;
    }

}
