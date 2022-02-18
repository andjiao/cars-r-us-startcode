package kea.sem3.jwtdemo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;

    public Reservation() {
    }

    @Column (name = "reservationDate")
    @CreationTimestamp
    LocalDate reservationDate;

    @Column (name = "rentalDate")
    private LocalDate rentalDate;

    @ManyToOne
    Car reservedCar;

    @ManyToOne
    Member reservedBy;

    public Reservation(LocalDate date, Car reservedCar, Member reservedBy) {
        this.reservationDate = date;
        this.reservedCar = reservedCar;
        this.reservedBy = reservedBy;
        reservedCar.addResevertaion(this);
        reservedBy.addResevertaion(this);

    }
}
