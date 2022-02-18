package kea.sem3.jwtdemo.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;

    public Reservation() {
    }

    @CreationTimestamp
    LocalDateTime created;

    LocalDate rentalDate;

    @ManyToOne
    Car reservedCar;

    @ManyToOne
    Member reservedBy;

    public Reservation(LocalDate date, Car reservedCar, Member reservedBy) {
        this.rentalDate = date;
        this.reservedCar = reservedCar;
        this.reservedBy = reservedBy;
        reservedCar.addResevertaion(this);
        reservedBy.addResevertaion(this);

    }
}
