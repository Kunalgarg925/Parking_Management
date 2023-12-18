package UserLevel;

import Model.CarType;
import Model.ParkingTicket;
import ServiceLevel.ParkingService;

public class ParkingManagement {
    public static void main(String[] args) {
        ParkingService parkingService = new ParkingService();
        ParkingTicket customer1 = new ParkingTicket();
        customer1.setCarName("XUV300");
        customer1.setCarType(CarType.Suv);

        ParkingTicket customer2 = new ParkingTicket();
        customer2.setCarName("Audi Q6");
        customer2.setCarType(CarType.SportsCars);

        String customer1Id = parkingService.alotParking(customer1,2);
        String customer2Id = parkingService.alotParking(customer2,3);
        System.out.println("--------------------------------\n");
        System.out.println("database -> " + parkingService.getAllParkingDetails());
        System.out.println("Parking slots left : " + parkingService.getRemainingSlots());

        parkingService.checkOut(customer1Id);
        System.out.println("Parking slots left : " + parkingService.getRemainingSlots());
        parkingService.checkOut(customer2Id);
        System.out.println("Parking slots left : " + parkingService.getRemainingSlots());

        System.out.println("--------------------------------\n");
        System.out.println("database -> " + parkingService.getAllParkingDetails());

    }
}
