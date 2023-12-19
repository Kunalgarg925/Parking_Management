import Model.CarType;
import Model.ParkingTicket;
import ServiceLevel.ParkingService;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ParkingManagementTest {
    ParkingService parkingService = new ParkingService();

    @BeforeClass
    public static void setup(){
        ParkingTicket pt1 = createTicket("Nexon", CarType.Suv);
        ParkingTicket pt2 = createTicket("Nexon", CarType.Suv);
        ParkingTicket pt3 = createTicket("Nexon", CarType.Suv);
        ParkingTicket pt4 = createTicket("Nexon", CarType.Suv);
        ParkingTicket pt5 = createTicket("Nexon", CarType.Suv);
        ParkingTicket pt6 = createTicket("Nexon", CarType.Suv);
    }
    @Test
    public void alotParkingToCustomer(){

        customer1.setTicketId(parkingService.alotParking(customer1,2));
        parkingService.alotParking(customer2,3);
        System.out.println("customer 1 alot ->" + customer1);
        System.out.println("customer 2 alot ->" + customer2);
        assertNotNull(parkingService.alotParking(customer1,2));
        assertNotNull(parkingService.alotParking(customer2,3));
        assertNotNull(parkingService.alotParking(customer3,5));
        assertNotNull(parkingService.alotParking(customer4,1));
        assertNotNull(parkingService.alotParking(customer7,8));
        assertThrows(RuntimeException.class, () -> {parkingService.alotParking(customer6,1);});
        assertThrows(NullPointerException.class, () -> {parkingService.alotParking(customer3,0);});
        assertThrows(NullPointerException.class, () -> {parkingService.alotParking(customer3,null);});
        assertThrows(NullPointerException.class, () -> {parkingService.alotParking(customer5,1);});
    }

    @Test
    void getAllParkingDetails(){
        assertNotNull(parkingService.getAllParkingDetails());
    }

    @Test
    void validateParkingSlotIsNotEmpty(){
        alotParkingToCustomer();
        assertEquals(0,parkingService.getRemainingSlots());
    }

    @Test
    void checkOutCustomerVechile(){
        System.out.println("checkout -> " + customer1.getTicketId());
//        alotParkingToCustomer();
        parkingService.checkOut(customer1.getTicketId());
        parkingService.checkOut(customer2.getTicketId());
        assertThrows(NullPointerException.class, () -> {parkingService.checkOut(customer6.getTicketId());});
    }
    @Test
    void getRemainingSlotAfterCheckout(){
        checkOutCustomerVechile();
        assertEquals(2,parkingService.getRemainingSlots());
    }

    private ParkingTicket createTicket(String carName, CarType carType) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setCarName(carName);
        parkingTicket.setCarType(carType);
        return parkingTicket;
    }

}
