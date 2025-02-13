package kea.sem3.jwtdemo.entity;

import kea.sem3.jwtdemo.dto.MemberRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@DiscriminatorValue("MEMBER")
public class Member extends BaseUser {

    String firstName;
    String lastName;
    String street;
    String city;
    int zip;
    boolean approved;
    String ranking;


    public Member(String username, String email, String password, String firstName) {
        super(username, email, password);
        this.firstName = firstName;
    }

    public Member() {
    }

    public Member (String username, String email, String password,String firstname, String lastname, String street, String city, int zip){
        super(username, email, password);

        this.firstName = firstname;
        this.lastName = lastname;
        this.street = street;
        this.city = city;
        this.zip = zip;

        this.approved = false;
        this.ranking = "5";
    }


    public Member(MemberRequest body) {
        this(body.getUsername(),body.getEmail(),body.getPassword(),body.getFirstName(),body.getLastName(),body.getStreet(),body.getCity(),body.getZip());
    }

    @OneToMany(mappedBy = "reservedBy")
    private Set<Reservation> reservations = new HashSet<>();

    public void addResevertaion (Reservation res){
        reservations.add(res);
    }

    @Column
    @CreationTimestamp
    private LocalDateTime created;

    @Column
    @UpdateTimestamp
    private LocalDateTime lastEdited;

    public Member(String username, String email, String password, String firstName, String lastName, String street, String city, int zip, boolean approved, String ranking) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.approved = approved;
        this.ranking = ranking;

    }

    //getting acces to attributes from parent-class
    public String getUsername() {
        return super.getUsername();
    }
    public String getEmail(){
        return super.getEmail();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return zip == member.zip && approved == member.approved && Objects.equals(firstName, member.firstName) && Objects.equals(lastName, member.lastName) && Objects.equals(street, member.street) && Objects.equals(city, member.city) && Objects.equals(ranking, member.ranking) && Objects.equals(created, member.created) && Objects.equals(lastEdited, member.lastEdited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, street, city, zip, approved, ranking, created, lastEdited);
    }

}