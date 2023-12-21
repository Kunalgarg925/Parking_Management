package model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingTicket {
    private String ticketId;
    private String carName;
    private CarType carType;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
}
