package host;

import model.CarType;
import model.ParkingTicket;
import service.ParkingService;

public class ParkingManagement {
    public static void main(String[] args) {
        ParkingService parkingService = new ParkingService();
        ParkingTicket ticket1 = new ParkingTicket();
        ticket1.setCarName("XUV300");
        ticket1.setCarType(CarType.Suv);

        ParkingTicket ticket2 = new ParkingTicket();
        ticket2.setCarName("Audi Q6");
        ticket2.setCarType(CarType.SportsCars);

        String ticket1Id = parkingService.alotParking(ticket1,2);
        String ticket2Id = parkingService.alotParking(ticket2,3);
        System.out.println("database -> " + parkingService.getAllParkingDetails());
        System.out.println("Parking slots left : " + parkingService.getRemainingSlots());

        parkingService.checkOut(ticket1Id);
        System.out.println("Parking slots left : " + parkingService.getRemainingSlots());
        parkingService.checkOut(ticket2Id);
        System.out.println("Parking slots left : " + parkingService.getRemainingSlots());

    }
}
