package ru.levelup.junior.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 32)
    private String firstName;

    @Column(length = 32)
    private String lastName;

    @Column()
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(nullable = false, unique = true, length = 10)
    private long passportNumber;

    @Column
    private String address;

    @Column(unique = true)
    private String eMail;

    @Column(unique = true, nullable = false, length = 32)
    private String login;

    @Column(nullable = false, length = 32)
    private String password;

    public Client() {
    }

    public Client(String firstName, String lastName, long passportNumber, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String surName) {
        this.lastName = surName;
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
