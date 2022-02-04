package kea.sem3.jwtdemo.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MEMBER")
public class Member extends BaseUser {

    private String fornavn;
    private String efternavn;
    private String gade;
    private String by;
    private String zip;
    private String approved;
    private String ranking;


    @Column(name="forNavn",length = 60, nullable = false )
    private String forNavn;

    @Column(name="efternavn",length = 60, nullable = false )
    private String efternavn;

    @Column(name="gade",length = 60, nullable = false )
    private String gade;

    @Column(name="by",length = 60, nullable = false )
    private String by;

    @Column(name="zip",length = 60, nullable = false )
    private String zip;

    @Column(name="approved",length = 60, nullable = false )
    private String approved;

    @Column(name="ranking",length = 60, nullable = false )
    private String ranking;


    public Member(String username, String email, String password, String forNavn, String efternavn, String gade, String by, String zip, String approved, String ranking) {
        super(username, email, password);
        this.forNavn = forNavn;
        this.efternavn = efternavn;
        this.gade = gade;
        this.by = by;
        this.zip = zip;
        this.approved = approved;
        this.ranking = ranking;
    }

    public Member() {
    }

    public String getForNavn() {
        return forNavn;
    }

    public void setForNavn(String forNavn) {
        this.forNavn = forNavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getGade() {
        return gade;
    }

    public void setGade(String gade) {
        this.gade = gade;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}
