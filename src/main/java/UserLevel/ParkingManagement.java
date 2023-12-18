package UserLevel;

import Model.CarType;
import Model.ParkingTicket;
import ServiceLevel.ParkingRepository;

public class ParkingManagement {
    public static void main(String[] args) {
        ParkingRepository parkingRepository = new ParkingRepository();
        ParkingTicket customer1 = new ParkingTicket();
        customer1.setCarName("XUV300");
        customer1.setCarType(CarType.Suv);

        ParkingTicket customer2 = new ParkingTicket();
        customer2.setCarName("Audi Q6");
        customer2.setCarType(CarType.SportsCars);

        String customer1Id = parkingRepository.alotParking(customer1,2);
        String customer2Id = parkingRepository.alotParking(customer2,3);
        System.out.println("--------------------------------\n");
        System.out.println("database -> " + parkingRepository.getAllParkingDetails());
        System.out.println("Parking slots left : " + parkingRepository.getRemainingSlots());

        parkingRepository.checkOut(customer1Id);
        System.out.println("Parking slots left : " + parkingRepository.getRemainingSlots());
        parkingRepository.checkOut(customer2Id);
        System.out.println("Parking slots left : " + parkingRepository.getRemainingSlots());

        System.out.println("--------------------------------\n");
        System.out.println("database -> " + parkingRepository.getAllParkingDetails());

    }
}
