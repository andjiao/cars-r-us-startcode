package kea.sem3.jwtdemo.service;


import kea.sem3.jwtdemo.dto.ReservationResponse;
import kea.sem3.jwtdemo.entity.Reservation;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.ReservationReporsitory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    ReservationReporsitory reservationReporsitory;
    CarRepository carRepository;

    public ReservationService(ReservationReporsitory reservationReporsitory, CarRepository carRepository){
        this.reservationReporsitory= reservationReporsitory;
        this.carRepository=carRepository;
    }

    public List<Reservation> getAllReservation(){
        return reservationReporsitory.findAll();
    }

    public ReservationResponse getReservation(int id){
        return new ReservationResponse(reservationReporsitory.findById(id).orElseThrow(()-> new Client4xxException("No car reservation id:" + id)));

    }
}
