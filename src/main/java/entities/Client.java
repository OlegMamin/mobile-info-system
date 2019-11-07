package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String surName;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(nullable = false, unique = true, length = 10)
    private long passportNumber;

    @Column
    private String address;


    @Column(unique = true)
    private String eMail;

    @OneToMany()
    private List<Contract> contracts;

    @Column(unique = true, nullable = false, length = 32)
    private String login;

    @Column(unique = true, nullable = false, length = 32)
    private String password;

    public Client() {
    }

    public Client(long passportNumber, String login, String password) {
        this.passportNumber = passportNumber;
        this.login = login;
        this.password = password;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(long passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
