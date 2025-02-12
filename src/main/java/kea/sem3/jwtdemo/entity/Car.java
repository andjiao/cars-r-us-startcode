package kea.sem3.jwtdemo.entity;


import kea.sem3.jwtdemo.dto.CarRequest;
/*Project Lombok is a java library tool that
is used to minimize/remove the boilerplate code and save the precious
time of developers during development by just using some annotations.


boilerplate =  boilerplate—are sections of code that are repeated in multiple places with little to no variation.*/

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


//Den skal hedde test til sidst for at kunne kører, tror jeg...????
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity (name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @Column(name="brand",length = 60, nullable = false )
    private String brand;

    @Column(name="model", length=40, nullable=false)
    private String model;

    @Column(name="pricePrDay", length=40, nullable=false)
    private double pricePrDay;

    private Double bestDiscount;


    public Car(String brand, String model, double pricePrDay, double discount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount=discount;
    }

    public Car(CarRequest body) {
        this.brand = body.getBrand();
        this.model = body.getModel();
        this.pricePrDay = body.getPricePrDay();
        this.bestDiscount= body.getPricePrDay();
    }

    public Car() {

    }

    @Column(name="oprettet")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name="edited")
    @UpdateTimestamp
    private LocalDateTime dateEdited;

    //if problems related to transaction, use eager

    // by deafult er værdien lazy i oneToMany
    @OneToMany(mappedBy = "reservedCar", fetch = FetchType.EAGER)
    // Removes the Getter & Setter for this
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Set<Reservation> reservations = new HashSet<>();

    public void addResevertaion (Reservation res){
        reservations.add(res);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPricePrDay() {
        return pricePrDay;
    }

    public void setPricePrDay(double pricePrDay) {
        this.pricePrDay = pricePrDay;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(LocalDateTime dateEdited) {
        this.dateEdited = dateEdited;
    }

    public Double getBestDiscount() {
        return bestDiscount;
    }


    public void setBestDiscount(Double bestDiscount) {
        this.bestDiscount = bestDiscount;
    }

    public Car(int id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", pricePrDay='" + pricePrDay + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateEdited=" + dateEdited +
                '}';
    }
}
